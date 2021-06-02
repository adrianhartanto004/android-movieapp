package com.adrian.home.presentation.popularmovieslist.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.adrian.abstraction.extension.loadImageUrl
import com.adrian.home.databinding.HolderPopularMoviesShowMoreListBinding
import com.adrian.home.domain.model.popularmovies.PopularMovies

internal class PopularMoviesListAdapter :
    RecyclerView.Adapter<PopularMoviesListAdapter.ViewHolder>() {

    private var popularMovies = arrayListOf<PopularMovies>()

    private var onClickListener: ((popularMovies: PopularMovies) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val holder = ViewHolder(
            HolderPopularMoviesShowMoreListBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
        return holder
    }

    override fun getItemCount(): Int = popularMovies.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentList = popularMovies[position]
        holder.binding.apply {
            ivPoster.loadImageUrl(currentList.posterPath)
            tvTitle.text = currentList.title
            tvRating.text = currentList.voteAverage.toString()
            tvVoteCount.text = currentList.voteCount.toString()
        }
        holder.itemView.setOnClickListener { onClickListener?.invoke(currentList) }
    }

    fun addMoreData(data: List<PopularMovies>) {
        val positionStart = data.size
        this.popularMovies.addAll(data)
        if (positionStart == 0) {
            notifyDataSetChanged()
        } else {
            notifyItemRangeInserted(positionStart, data.size)
        }
    }

    fun clearAllElements() {
        popularMovies.clear()
        notifyDataSetChanged()
    }

    fun setOnItemClickedListener(listener: (popularMovies: PopularMovies) -> Unit) {
        this.onClickListener = listener
    }

    internal inner class ViewHolder(val binding: HolderPopularMoviesShowMoreListBinding) :
        RecyclerView.ViewHolder(binding.root)
}
