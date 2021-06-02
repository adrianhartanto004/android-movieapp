package com.adrian.home.presentation.moviedetail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.adrian.home.data.network.model.genre.GenreJson
import com.adrian.home.databinding.HolderMovieDetailGenresBinding

internal class MovieDetailGenreAdapter :
    RecyclerView.Adapter<MovieDetailGenreAdapter.ViewHolder>() {

    private var genreListJson = listOf<GenreJson>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val holder = ViewHolder(
            HolderMovieDetailGenresBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
        return holder
    }

    override fun getItemCount(): Int = genreListJson.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentList = genreListJson[position]
        holder.binding.apply {
            tvGenre.text = currentList.name
        }
    }

    fun submitList(genreListJson: List<GenreJson>) {
        this.genreListJson = genreListJson
        notifyDataSetChanged()
    }

    internal inner class ViewHolder(val binding: HolderMovieDetailGenresBinding) :
        RecyclerView.ViewHolder(binding.root)
}
