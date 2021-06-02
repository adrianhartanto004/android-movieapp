package com.adrian.home.presentation.home.adapter

import android.os.Parcelable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.adrian.home.databinding.HolderPopularMoviesBinding

internal class PopularMoviesAdapter(private val popularMoviesItemAdapter: PopularMoviesItemAdapter) : RecyclerView.Adapter<PopularMoviesAdapter.ViewHolder>() {

    private lateinit var recyclerViewManagerState: Parcelable

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val holder = ViewHolder(
            HolderPopularMoviesBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
        holder.setIsRecyclable(false)
        return holder
    }

    private var onShowMoreClickListener: (() -> Unit)? = null

    override fun getItemCount(): Int = 1

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.rvList.apply {
            adapter = popularMoviesItemAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            if (this@PopularMoviesAdapter::recyclerViewManagerState.isInitialized) {
                layoutManager?.onRestoreInstanceState(recyclerViewManagerState)
            }
            setHasFixedSize(true)
            isNestedScrollingEnabled = false
        }

        holder.binding.tvShowAll.setOnClickListener { onShowMoreClickListener?.invoke() }
    }

    override fun onViewDetachedFromWindow(holder: ViewHolder) {
        if (holder.adapterPosition == RecyclerView.NO_POSITION) {
            return
        }

        holder.binding.rvList.layoutManager?.onSaveInstanceState()?.let {
            recyclerViewManagerState = it
        }
    }
    fun setOnShowMoreClickListener(listener: () -> Unit) {
        this.onShowMoreClickListener = listener
    }


    internal inner class ViewHolder(val binding: HolderPopularMoviesBinding) :
        RecyclerView.ViewHolder(binding.root)
}
