package com.adrian.home.presentation.moviedetail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.adrian.abstraction.extension.loadImageUrl
import com.adrian.home.data.network.model.moviephoto.Backdrop
import com.adrian.home.databinding.HolderMovieDetailPhotoItemBinding

internal class MovieDetailPhotoItemAdapter :
    RecyclerView.Adapter<MovieDetailPhotoItemAdapter.ViewHolder>() {

    private var photos = listOf<Backdrop>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val holder = ViewHolder(
            HolderMovieDetailPhotoItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
        return holder
    }

    override fun getItemCount(): Int = photos.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentList = photos[position]
        holder.binding.apply {
            if (currentList.filePath != null) {
                ivProfile.loadImageUrl(currentList.filePath)
            }
        }
    }

    fun submitList(photos: List<Backdrop>) {
        this.photos = photos
        notifyDataSetChanged()
    }

    internal inner class ViewHolder(val binding: HolderMovieDetailPhotoItemBinding) :
        RecyclerView.ViewHolder(binding.root)
}
