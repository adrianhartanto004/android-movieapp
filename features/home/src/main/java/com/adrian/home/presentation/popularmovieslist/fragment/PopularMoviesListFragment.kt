package com.adrian.home.presentation.popularmovieslist.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.adrian.abstraction.common.state.UIState
import com.adrian.abstraction.common.state.onFailure
import com.adrian.abstraction.common.state.onLoading
import com.adrian.abstraction.common.state.onSuccess
import com.adrian.abstraction.extension.observe
import com.adrian.abstraction.extension.showLongToast
import com.adrian.abstraction.presentation.fragment.BaseFragment
import com.adrian.abstraction.widget.recyclerviewloadmore.RecyclerViewLoadMoreListener
import com.adrian.home.R
import com.adrian.home.databinding.FragmentPopularMoviesListBinding
import com.adrian.home.domain.model.popularmovies.PopularMovies
import com.adrian.home.presentation.popularmovieslist.adapter.PopularMoviesListAdapter
import com.adrian.home.presentation.popularmovieslist.viewmodel.PopularMoviesListViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class PopularMoviesListFragment : BaseFragment(R.layout.fragment_popular_movies_list),
    SwipeRefreshLayout.OnRefreshListener {

    private lateinit var binding: FragmentPopularMoviesListBinding

    private val popularMoviesListViewModel: PopularMoviesListViewModel by viewModel()

    private lateinit var popularMoviesListAdapter: PopularMoviesListAdapter

    private lateinit var recyclerViewLoadMoreListener: RecyclerViewLoadMoreListener

    private val popularMoviesObserver = Observer<UIState<List<PopularMovies>>> { state ->
        state onLoading {
            binding.swipeRefresh.isRefreshing = true
        }
        state onSuccess {
            binding.swipeRefresh.isRefreshing = false
            if (data != null) {
                popularMoviesListAdapter.addMoreData(data ?: listOf())
                recyclerViewLoadMoreListener.updateStateAfterGetData(popularMoviesListViewModel.totalPage)
            } else {
            }
            recyclerViewLoadMoreListener.hideRecyclerViewLoading()
            popularMoviesListAdapter.notifyDataSetChanged()
            binding.swipeRefresh.isEnabled = true
        }
        state onFailure {
            showLongToast(message)
            binding.swipeRefresh.isRefreshing = false
            binding.swipeRefresh.isEnabled = true
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPopularMoviesListBinding.inflate(inflater, container, false)
        super.onCreate(savedInstanceState)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        if (!popularMoviesListViewModel.apiDataReceived) {
            reloadPage()
            popularMoviesListViewModel.apiDataReceived = true
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setToolbar(binding.toolbar)
        init()
    }

    private fun setRecyclerViewAdapter() {
        popularMoviesListAdapter = PopularMoviesListAdapter()
        binding.apply {
            rvList.layoutManager = LinearLayoutManager(context)
            rvList.adapter = popularMoviesListAdapter
        }
    }

    private fun init() {
        setSwipeToRefresh()
        setRecyclerViewAdapter()
        setRecyclerViewLoadMore()
        observe(popularMoviesListViewModel.popularMoviesLiveData, popularMoviesObserver)

        popularMoviesListAdapter.setOnItemClickedListener {
            val navDirections =
                PopularMoviesListFragmentDirections.actionPopularMoviesToMovieDetail(it.id)
            findNavController().navigate(navDirections)
        }
    }

    private fun createRecyclerViewLoadMoreListener(): RecyclerViewLoadMoreListener {
        return object :
            RecyclerViewLoadMoreListener(binding.rvList.layoutManager as LinearLayoutManager) {
            override fun loadMoreData(currentPage: Int) {
                popularMoviesListViewModel.getPopularMovies(currentPage)
            }
        }
    }

    private fun setRecyclerViewLoadMore() {
        recyclerViewLoadMoreListener = createRecyclerViewLoadMoreListener()
        binding.rvList.addOnScrollListener(recyclerViewLoadMoreListener)
    }

    private fun setSwipeToRefresh() {
        binding.swipeRefresh.apply {
            setOnRefreshListener(this@PopularMoviesListFragment)
            isEnabled = false
            isRefreshing = true
        }
    }

    private fun reloadPage() {
        recyclerViewLoadMoreListener.resetState()
        popularMoviesListViewModel.totalPage = 0
        popularMoviesListAdapter.clearAllElements()
        popularMoviesListViewModel.getPopularMovies(1)
    }

    override fun onRefresh() {
        reloadPage()
    }
}