package com.adrian.home.presentation.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.adrian.abstraction.extension.loadImageUrl
import com.adrian.home.databinding.HolderNowPlayingMoviesItemBinding
import com.adrian.home.domain.model.nowplayingmovies.NowPlayingMovies

internal class NowPlayingMoviesItemAdapter :
    RecyclerView.Adapter<NowPlayingMoviesItemAdapter.ViewHolder>() {

    private var nowPlayingMovies = listOf<NowPlayingMovies>()

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
            tvGenre.text
        }
    }

    fun submitList(popularMovies: List<NowPlayingMovies>) {
        this.nowPlayingMovies = popularMovies
        notifyDataSetChanged()
    }

    internal inner class ViewHolder(val binding: HolderNowPlayingMoviesItemBinding) :
        RecyclerView.ViewHolder(binding.root)
}
