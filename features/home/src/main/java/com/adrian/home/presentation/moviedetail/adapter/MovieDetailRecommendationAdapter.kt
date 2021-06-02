package com.adrian.home.presentation.moviedetail.adapter

import android.os.Parcelable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.adrian.home.databinding.HolderMovieDetailRecommendationBinding

internal class MovieDetailRecommendationAdapter(private val movieDetailRecommendationItemAdapter: MovieDetailRecommendationItemAdapter) :
    RecyclerView.Adapter<MovieDetailRecommendationAdapter.ViewHolder>() {

    private lateinit var recyclerViewManagerState: Parcelable

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val holder = ViewHolder(
            HolderMovieDetailRecommendationBinding.inflate(
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
        holder.binding.rvRecommendationList.apply {
            adapter = movieDetailRecommendationItemAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            if (this@MovieDetailRecommendationAdapter::recyclerViewManagerState.isInitialized) {
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

        holder.binding.rvRecommendationList.layoutManager?.onSaveInstanceState()?.let {
            recyclerViewManagerState = it
        }
    }

    internal inner class ViewHolder(val binding: HolderMovieDetailRecommendationBinding) :
        RecyclerView.ViewHolder(binding.root)
}
