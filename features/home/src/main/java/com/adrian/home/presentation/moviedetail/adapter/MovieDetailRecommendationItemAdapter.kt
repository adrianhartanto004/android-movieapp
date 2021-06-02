package com.adrian.home.presentation.moviedetail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.adrian.abstraction.extension.loadImageUrl
import com.adrian.home.databinding.HolderMovieDetailRecommendationItemBinding
import com.adrian.home.domain.model.recommendedmovies.RecommendedMovies

internal class MovieDetailRecommendationItemAdapter :
    RecyclerView.Adapter<MovieDetailRecommendationItemAdapter.ViewHolder>() {

    private var movies = listOf<RecommendedMovies>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val holder = ViewHolder(
            HolderMovieDetailRecommendationItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
        return holder
    }

    override fun getItemCount(): Int = movies.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentList = movies[position]
        holder.binding.apply {
            ivPoster.loadImageUrl(currentList.posterPath)
            tvTitle.text = currentList.title
            tvDate.text = currentList.releaseDate
        }
    }

    fun submitList(movies: List<RecommendedMovies>) {
        this.movies = movies
        notifyDataSetChanged()
    }

    internal inner class ViewHolder(val binding: HolderMovieDetailRecommendationItemBinding) :
        RecyclerView.ViewHolder(binding.root)
}
