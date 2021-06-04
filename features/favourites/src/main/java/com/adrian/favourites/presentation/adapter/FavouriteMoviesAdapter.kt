package com.adrian.favourites.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.adrian.abstraction.common.domain.model.FavouriteMovie
import com.adrian.abstraction.extension.loadImageUrl
import com.adrian.favourites.databinding.HolderFavouriteMovieListBinding

internal class FavouriteMoviesAdapter :
    RecyclerView.Adapter<FavouriteMoviesAdapter.ViewHolder>() {

    private var favouriteMovies = listOf<FavouriteMovie>()

    private var onClickListener: ((favouriteMovie: FavouriteMovie) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val holder = ViewHolder(
            HolderFavouriteMovieListBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
        return holder
    }

    override fun getItemCount(): Int = favouriteMovies.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentList = favouriteMovies[position]
        holder.binding.apply {
            ivPoster.loadImageUrl(currentList.posterPath)
            tvTitle.text = currentList.title
            tvRating.text = currentList.voteAverage.toString()
            tvVoteCount.text = currentList.voteCount.toString()
        }
        holder.itemView.setOnClickListener { onClickListener?.invoke(currentList) }
    }

    fun submitList(favouriteMovies: List<FavouriteMovie>) {
        this.favouriteMovies = favouriteMovies
        notifyDataSetChanged()
    }

    fun setOnItemClickedListener(listener: (favouriteMovie: FavouriteMovie) -> Unit) {
        this.onClickListener = listener
    }

    internal inner class ViewHolder(val binding: HolderFavouriteMovieListBinding) :
        RecyclerView.ViewHolder(binding.root)
}
