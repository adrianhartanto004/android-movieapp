package com.adrian.home.presentation.moviedetail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.adrian.abstraction.extension.loadImageUrl
import com.adrian.home.data.network.model.moviedetail.MovieDetailResponseJson
import com.adrian.home.databinding.HolderMovieDetailStorylineBinding
import com.google.android.flexbox.*

internal class MovieDetailStorylineAdapter(private val movieDetailGenreAdapter: MovieDetailGenreAdapter) :
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
        holder.setIsRecyclable(false)
        return holder
    }

    override fun getItemCount(): Int = 1

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentList = movieDetailResponseJson
        holder.binding.apply {
            ivPoster.loadImageUrl(currentList.posterPath)
            tvTitle.text = currentList.title
            tvRating.text = currentList.voteAverage.toString()
            tvVoteCount.text = "(${currentList.voteCount.toString()})"
            tvReleaseDate.text = "Release date: ${currentList.releaseDate}"
            tvMovieLanguage.text = "Original language: ${currentList.originalLanguage}"
            tvProductionCompany.text =
                "Production company: ${currentList.productionCompanies?.map { it.name }?.joinToString(", ")}"
            tvProductionCountries.text =
                "Production countries: ${currentList.productionCountries?.map { it.name }?.joinToString(", ")}"
            if(currentList.budget == 0){
                tvBudget.text = "Budget: unknown"
            } else{
                tvBudget.text = "Budget: $${currentList.budget.toString()}"
            }
            tvRevenue.text = "Revenue: $${currentList.revenue.toString()}"
            tvOverviewDesc.text = currentList.overview

            rvGenre.apply {
                val flexboxLayoutManager = FlexboxLayoutManager(context)
                flexboxLayoutManager.flexWrap = FlexWrap.WRAP
                flexboxLayoutManager.flexDirection = FlexDirection.ROW
                flexboxLayoutManager.justifyContent = JustifyContent.FLEX_START
                flexboxLayoutManager.alignItems = AlignItems.FLEX_START
                layoutManager = flexboxLayoutManager
                adapter = movieDetailGenreAdapter
                setHasFixedSize(true)
                isNestedScrollingEnabled = false
            }
            currentList.genres?.let { movieDetailGenreAdapter.submitList(it) }
        }
    }

    fun submitList(movieDetailResponseJson: MovieDetailResponseJson) {
        this.movieDetailResponseJson = movieDetailResponseJson
        notifyDataSetChanged()
    }

    internal inner class ViewHolder(val binding: HolderMovieDetailStorylineBinding) :
        RecyclerView.ViewHolder(binding.root)
}
