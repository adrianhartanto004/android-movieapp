package com.adrian.home.presentation.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.adrian.abstraction.extension.loadImageUrl
import com.adrian.home.databinding.HolderNowPlayingMoviesItemBinding
import com.adrian.home.domain.model.genre.Genre
import com.adrian.home.domain.model.nowplayingmovies.NowPlayingMovies

internal class NowPlayingMoviesItemAdapter :
    RecyclerView.Adapter<NowPlayingMoviesItemAdapter.ViewHolder>() {

    private var nowPlayingMovies = listOf<NowPlayingMovies>()

    private var genres = listOf<Genre>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val holder = ViewHolder(
            HolderNowPlayingMoviesItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
        return holder
    }

    override fun getItemCount(): Int = nowPlayingMovies.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentList = nowPlayingMovies[position]
        holder.binding.apply {
            ivPoster.loadImageUrl(currentList.posterPath)
            tvTitle.text = currentList.title
            val genreNames = arrayListOf<String>()
            for (currentGenreId in currentList.genreIds) {
                for (genre in genres) {
                    if (genre.id == currentGenreId) {
                        genreNames.add(genre.name)
                    }
                }
            }
            tvGenre.text = genreNames.joinToString(", ")
        }
    }

    fun submitList(popularMovies: List<NowPlayingMovies>) {
        this.nowPlayingMovies = popularMovies
        notifyDataSetChanged()
    }

    fun setGenres(genres: List<Genre>) {
        this.genres = genres
        notifyDataSetChanged()
    }

    internal inner class ViewHolder(val binding: HolderNowPlayingMoviesItemBinding) :
        RecyclerView.ViewHolder(binding.root)
}
