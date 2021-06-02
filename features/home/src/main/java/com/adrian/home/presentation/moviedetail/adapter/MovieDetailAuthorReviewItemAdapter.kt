package com.adrian.home.presentation.moviedetail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.adrian.abstraction.extension.loadImageUrlCircleCrop
import com.adrian.home.R
import com.adrian.home.data.network.model.authorreview.AuthorReview
import com.adrian.home.databinding.HolderMovieDetailReviewItemBinding

internal class MovieDetailAuthorReviewItemAdapter :
    RecyclerView.Adapter<MovieDetailAuthorReviewItemAdapter.ViewHolder>() {

    private var authorReview = arrayListOf<AuthorReview>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val holder = ViewHolder(
            HolderMovieDetailReviewItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
        return holder
    }

    override fun getItemCount(): Int = authorReview.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentList = authorReview[position]
        holder.binding.apply {
            if (currentList.authorDetails?.avatarPath != null) {
                ivProfile.loadImageUrlCircleCrop(currentList.authorDetails.avatarPath)
            } else {
                ivProfile.setImageResource(R.drawable.ic_profile_circle_black)
            }
            tvProfile.text = currentList.author
            tvRating.text = currentList.authorDetails?.rating.toString()
            tvReviewDate.text = currentList.updatedAt?.substring(0, 10)
        }
    }

    fun addMoreData(data: List<AuthorReview>) {
        val positionStart = data.size
        this.authorReview.addAll(data)
        if (positionStart == 0) {
            notifyDataSetChanged()
        } else {
            notifyItemRangeInserted(positionStart, data.size)
        }
    }

    fun clearAllElements() {
        authorReview.clear()
        notifyDataSetChanged()
    }

    internal inner class ViewHolder(val binding: HolderMovieDetailReviewItemBinding) :
        RecyclerView.ViewHolder(binding.root)
}
