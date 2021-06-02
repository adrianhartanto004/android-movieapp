package com.adrian.home.presentation.moviedetail.adapter

import android.os.Parcelable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.adrian.home.databinding.HolderMovieDetailPhotoBinding

internal class MovieDetailPhotoAdapter(private val movieDetailPhotoItemAdapter: MovieDetailPhotoItemAdapter) :
    RecyclerView.Adapter<MovieDetailPhotoAdapter.ViewHolder>() {

    private lateinit var recyclerViewManagerState: Parcelable

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val holder = ViewHolder(
            HolderMovieDetailPhotoBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
        holder.setIsRecyclable(false)
        return holder
    }

    override fun getItemCount(): Int = 1

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.rvPhoto.apply {
            adapter = movieDetailPhotoItemAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            if (this@MovieDetailPhotoAdapter::recyclerViewManagerState.isInitialized) {
                layoutManager?.onRestoreInstanceState(recyclerViewManagerState)
            }
            setHasFixedSize(true)
            isNestedScrollingEnabled = false
        }
    }

    override fun onViewDetachedFromWindow(holder: ViewHolder) {
        if (holder.adapterPosition == RecyclerView.NO_POSITION) {
            return
        }

        holder.binding.rvPhoto.layoutManager?.onSaveInstanceState()?.let {
            recyclerViewManagerState = it
        }
    }

    internal inner class ViewHolder(val binding: HolderMovieDetailPhotoBinding) :
        RecyclerView.ViewHolder(binding.root)
}