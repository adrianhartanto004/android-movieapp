package com.adrian.home.presentation.moviedetail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.adrian.abstraction.extension.loadImageUrl
import com.adrian.home.data.network.model.moviedetail.MovieDetailResponseJson
import com.adrian.home.databinding.HolderMovieDetailStorylineBinding

internal class MovieDetailStorylineAdapter :
    RecyclerView.Adapter<MovieDetailStorylineAdapter.ViewHolder>() {

    private var movieDetailResponseJson = MovieDetailResponseJson()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val holder = ViewHolder(
            HolderMovieDetailStorylineBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
        return holder
    }

    override fun getItemCount(): Int = 1

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentList = movieDetailResponseJson
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
            tvReleaseDate.text = currentList.releaseDate
            tvMovieLanguage.text = currentList.originalLanguage
            tvProductionCompany.text =
                currentList.productionCompanies?.map { it.name }?.joinToString(", ")
            tvProductionCountries.text =
                currentList.productionCountries?.map { it.name }?.joinToString(", ")
            tvBudget.text = currentList.budget.toString()
            tvRevenue.text = currentList.revenue.toString()
            tvOverviewDesc.text = currentList.overview
        }
    }

    fun submitList(movieDetailResponseJson: MovieDetailResponseJson) {
        this.movieDetailResponseJson = movieDetailResponseJson
        notifyDataSetChanged()
    }

    internal inner class ViewHolder(val binding: HolderMovieDetailStorylineBinding) :
        RecyclerView.ViewHolder(binding.root)
}
