package com.adrian.home.presentation.moviedetail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.adrian.home.R
import com.adrian.home.data.network.model.authorreview.AuthorReviewListJson
import com.adrian.home.databinding.HolderMovieDetailReviewBinding

internal class MovieDetailAuthorReviewAdapter :
    RecyclerView.Adapter<MovieDetailAuthorReviewAdapter.ViewHolder>() {

    private var authorReview: AuthorReviewListJson? = AuthorReviewListJson()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val holder = ViewHolder(
            HolderMovieDetailReviewBinding.inflate(
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
        holder.binding.apply {
            val reviewCountText = "${authorReview?.totalResults} ${
                tvReviewCount.context.getString(
                    R.string.movie_detail_author_review_text
                )
            }"
            tvReviewCount.text = reviewCountText
        }
    }

    fun submitList(authorReview: AuthorReviewListJson?) {
        this.authorReview = authorReview
        notifyDataSetChanged()
    }

    internal inner class ViewHolder(val binding: HolderMovieDetailReviewBinding) :
        RecyclerView.ViewHolder(binding.root)
}
