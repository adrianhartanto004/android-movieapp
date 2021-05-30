package com.adrian.home.presentation.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.adrian.abstraction.extension.loadImageUrl
import com.adrian.home.databinding.HolderPopularMoviesItemBinding
import com.adrian.home.domain.model.PopularMovies

internal class PopularMoviesItemAdapter :
    RecyclerView.Adapter<PopularMoviesItemAdapter.ViewHolder>() {

    private var popularMovies = listOf<PopularMovies>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val holder = ViewHolder(
            HolderPopularMoviesItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
//        holder.setIsRecyclable(false)
        return holder
    }

    override fun getItemCount(): Int = popularMovies.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentList = popularMovies[position]
        holder.binding.apply {
            ivPoster.loadImageUrl(currentList.posterPath)
            tvTitle.text = currentList.title
            tvRating.text = currentList.voteAverage.toString()

            val voteCount = currentList.voteCount.toString()
            var voteCountText = voteCount
            voteCountText = if (voteCount.length > 7) {
                "(${voteCount.substring(0, 7)})"
            } else {
                "($voteCountText)"
            }
            tvVoteCount.text = voteCountText
        }
    }

    fun submitList(popularMovies: List<PopularMovies>) {
        this.popularMovies = popularMovies
        notifyDataSetChanged()
    }

    internal inner class ViewHolder(val binding: HolderPopularMoviesItemBinding) :
        RecyclerView.ViewHolder(binding.root)
}
