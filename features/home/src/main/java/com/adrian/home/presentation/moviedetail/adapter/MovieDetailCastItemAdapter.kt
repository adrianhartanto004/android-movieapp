package com.adrian.home.presentation.moviedetail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.adrian.abstraction.extension.loadImageUrlCircleCrop
import com.adrian.home.R
import com.adrian.home.data.network.model.moviecredits.Cast
import com.adrian.home.databinding.HolderMovieDetailCastItemBinding

internal class MovieDetailCastItemAdapter :
    RecyclerView.Adapter<MovieDetailCastItemAdapter.ViewHolder>() {

    private var casts = listOf<Cast>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val holder = ViewHolder(
            HolderMovieDetailCastItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
        return holder
    }

    override fun getItemCount(): Int = casts.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentList = casts[position]
        holder.binding.apply {
            tvProfile.text = currentList.name
            if (currentList.profilePath != null) {
                ivProfile.loadImageUrlCircleCrop(currentList.profilePath)
            } else {
                ivProfile.setImageResource(R.drawable.ic_profile_circle_black)
            }
        }
    }

    fun submitList(casts: List<Cast>) {
        this.casts = casts
        notifyDataSetChanged()
    }

    internal inner class ViewHolder(val binding: HolderMovieDetailCastItemBinding) :
        RecyclerView.ViewHolder(binding.root)
}
