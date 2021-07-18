package com.adrian.home.presentation.moviedetail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.adrian.abstraction.extension.loadImageUrl
import com.adrian.home.R
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
        val context = holder.itemView.context
        holder.binding.apply {
            ivPoster.loadImageUrl(currentList.posterPath)
            tvTitle.text = currentList.title
            tvRating.text = currentList.voteAverage.toString()

            val tvVoteCountText = "(${currentList.voteCount.toString()})"
            tvVoteCount.text = tvVoteCountText

            val releaseDateText =
                "${context.getString(R.string.movie_detail_storyline_release_date)} ${currentList.releaseDate}"
            tvReleaseDate.text = releaseDateText

            val originalLanguageText =
                "${context.getString(R.string.movie_detail_storyline_original_language)} ${currentList.originalLanguage}"
            tvMovieLanguage.text = originalLanguageText

            val productionCompanyText =
                "${context.getString(R.string.movie_detail_storyline_production_company)} ${
                    currentList.productionCompanies?.map { it.name }?.joinToString(", ")
                }"
            tvProductionCompany.text = productionCompanyText

            val productionCountriesText =
                "${context.getString(R.string.movie_detail_storyline_production_countries)} ${
                    currentList.productionCountries?.map { it.name }?.joinToString(", ")
                }"
            tvProductionCountries.text = productionCountriesText

            if (currentList.budget == 0) {
                val emptyBudgetText =
                    context.getString(R.string.movie_detail_storyline_budget_unknown)
                tvBudget.text = emptyBudgetText
            } else {
                val budgetText =
                    "${context.getString(R.string.movie_detail_storyline_budget)}${currentList.budget.toString()}"
                tvBudget.text = budgetText
            }

            val revenueText =
                "${context.getString(R.string.movie_detail_storyline_revenue)}${currentList.revenue.toString()}"
            tvRevenue.text = revenueText

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
