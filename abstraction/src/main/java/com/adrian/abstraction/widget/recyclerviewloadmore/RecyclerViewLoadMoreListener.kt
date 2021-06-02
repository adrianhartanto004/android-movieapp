package com.adrian.abstraction.widget.recyclerviewloadmore

import android.view.View
import android.widget.AbsListView
import android.widget.ProgressBar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlin.properties.Delegates

abstract class RecyclerViewLoadMoreListener(layoutManager: LinearLayoutManager) : RecyclerView.OnScrollListener() {

    private var isRecyclerViewLoading by Delegates.notNull<Boolean>()
    private var visibleThreshold = 10
    private var STARTING_PAGE_INDEX = 1
    private var currentPage = 1
    private var hasNextPage = true
    private var currentItemCount = 0
    private var isScrolling = false
    private var layoutManager: RecyclerView.LayoutManager = layoutManager
    private var progressBar: ProgressBar? = null

    init {
        resetState()
    }

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        checkLoadMore(recyclerView, dx, dy)
        if (dy > 0) {
            hideFloating()
        } else {
            showFloating()
        }
    }

    override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
        super.onScrollStateChanged(recyclerView, newState)
        if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
            isScrolling = true
        }
    }

    fun updateStateAfterGetData(totalPage: Int?) {
        isRecyclerViewLoading = false
        val totalItemCount = layoutManager.itemCount
        if (totalItemCount > currentItemCount) {
            currentItemCount = totalItemCount
            currentPage++
        }
        checkIsAtLastPage(totalPage)
    }

    private fun checkIsAtLastPage(totalPage: Int?) {
        if (currentPage == totalPage) {
            hasNextPage = false
        }
    }

    private fun checkLoadMore(recyclerView: RecyclerView, dx: Int, dy: Int) {
        if (dx <= 0 && dy <= 0) {
            return
        }
        if (isRecyclerViewLoading) {
            return
        }
        if (isDataEmpty()) {
            return
        }
        val linearLayoutManager = recyclerView.layoutManager as LinearLayoutManager
        val countItem = linearLayoutManager.itemCount
        val firstVisibleItemPosition = linearLayoutManager.findFirstVisibleItemPosition()
        val visibleItemCount = linearLayoutManager.childCount
        val isAtLastItem = firstVisibleItemPosition + visibleItemCount >= countItem
        if ((isAtLastItem) && hasNextPage && isScrolling) {
            showRecyclerViewLoading()
            loadMoreData(currentPage)
            isScrolling = false
        }
    }

    private fun isDataEmpty(): Boolean {
        val totalItemCount = layoutManager.itemCount
        return totalItemCount == 0
    }

    fun resetState() {
        this.currentPage = STARTING_PAGE_INDEX
        this.currentItemCount = 0
        this.isRecyclerViewLoading = false
        this.hasNextPage = true
    }

    fun setProgressBar(progressBar: ProgressBar) {
        this.progressBar = progressBar
    }

    fun showRecyclerViewLoading() {
        isRecyclerViewLoading = true
        progressBar?.visibility = View.VISIBLE
    }

    fun hideRecyclerViewLoading() {
        isRecyclerViewLoading = false
        progressBar?.visibility = View.GONE
    }

    fun setStartingPage(startingPage: Int) {
        this.currentPage = startingPage
        this.STARTING_PAGE_INDEX = startingPage
    }

    fun setVisibleThreshold(visibleThreshold: Int) {
        this.visibleThreshold = visibleThreshold
    }

    fun getVisibleThreshold(): Int {
        return visibleThreshold
    }

    fun getCurrentPage(): Int {
        return currentPage
    }

    open fun showFloating() {}
    open fun hideFloating() {}
    abstract fun loadMoreData(currentPage: Int)
}