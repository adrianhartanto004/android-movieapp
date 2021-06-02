package com.adrian.home.presentation.moviedetail.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.adrian.abstraction.common.state.UIState
import com.adrian.abstraction.common.state.onFailure
import com.adrian.abstraction.common.state.onLoading
import com.adrian.abstraction.common.state.onSuccess
import com.adrian.abstraction.extension.observe
import com.adrian.abstraction.extension.showLongToast
import com.adrian.abstraction.presentation.fragment.BaseFragment
import com.adrian.home.R
import com.adrian.home.data.network.model.moviecredits.MovieCreditListJson
import com.adrian.home.data.network.model.moviedetail.MovieDetailResponseJson
import com.adrian.home.data.network.model.moviephoto.Backdrop
import com.adrian.home.data.network.model.moviephoto.MoviesPhotoListJson
import com.adrian.home.databinding.FragmentMovieDetailBinding
import com.adrian.home.domain.model.recommendedmovies.RecommendedMovies
import com.adrian.home.presentation.moviedetail.adapter.*
import com.adrian.home.presentation.moviedetail.viewmodel.MovieDetailViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MovieDetailFragment : BaseFragment(R.layout.fragment_movie_detail),
    SwipeRefreshLayout.OnRefreshListener {

    private lateinit var binding: FragmentMovieDetailBinding

    private val viewModel: MovieDetailViewModel by viewModels()

    private lateinit var movieDetailStorylineAdapter: MovieDetailStorylineAdapter
    private lateinit var movieDetailGenreAdapter: MovieDetailGenreAdapter
    private lateinit var movieDetailCastAdapter: MovieDetailCastAdapter
    private lateinit var movieDetailCastItemAdapter: MovieDetailCastItemAdapter
    private lateinit var movieDetailPhotoAdapter: MovieDetailPhotoAdapter
    private lateinit var movieDetailPhotoItemAdapter: MovieDetailPhotoItemAdapter
    private lateinit var movieDetailRecommendationAdapter: MovieDetailRecommendationAdapter
    private lateinit var movieDetailRecommendationItemAdapter: MovieDetailRecommendationItemAdapter
    private lateinit var concatAdapter: ConcatAdapter

    private val movieDetailObserver = Observer<UIState<MovieDetailResponseJson>> { state ->
        state onLoading {
            binding.swipeRefresh.isRefreshing = true
        }
        state onSuccess {
            if (data != null) {
                movieDetailStorylineAdapter.submitList(data ?: MovieDetailResponseJson())
                binding.rvConcat.isVisible = true
            } else {
                binding.rvConcat.isVisible = false
            }
            binding.swipeRefresh.isRefreshing = false
            binding.swipeRefresh.isEnabled = true
        }
        state onFailure {
            showLongToast(message)
            binding.swipeRefresh.isRefreshing = false
            binding.swipeRefresh.isEnabled = true
        }
    }

    private val movieCreditsObserver = Observer<UIState<MovieCreditListJson>> { state ->
        state onLoading {
        }
        state onSuccess {
            movieDetailCastItemAdapter.submitList(data?.cast ?: listOf())
        }
        state onFailure {
            showLongToast(message)
        }
    }

    private val moviePhotosObserver = Observer<UIState<List<Backdrop>>> { state ->
        state onLoading {
        }
        state onSuccess {
            movieDetailPhotoItemAdapter.submitList(data ?: listOf())
        }
        state onFailure {
            showLongToast(message)
        }
    }

    private val recommendedMoviesObserver = Observer<UIState<List<RecommendedMovies>>> { state ->
        state onLoading {
        }
        state onSuccess {
            movieDetailRecommendationItemAdapter.submitList(data ?: listOf())
        }
        state onFailure {
            showLongToast(message)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMovieDetailBinding.inflate(inflater, container, false)
        super.onCreate(savedInstanceState)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setToolbar(binding.toolbar)
        init()
    }

    private fun init() {
        setSwipeToRefresh()
        setRecyclerView()
        observe(viewModel.movieDetailLiveData, movieDetailObserver)
        observe(viewModel.movieCreditsLiveData, movieCreditsObserver)
        observe(viewModel.moviePhotosLiveData, moviePhotosObserver)
        observe(viewModel.recommendedMoviesLiveData, recommendedMoviesObserver)
        viewModel.loadData()
    }

    private fun setRecyclerView() {
        movieDetailGenreAdapter = MovieDetailGenreAdapter()
        movieDetailStorylineAdapter = MovieDetailStorylineAdapter(movieDetailGenreAdapter)
        movieDetailCastItemAdapter = MovieDetailCastItemAdapter()
        movieDetailCastAdapter = MovieDetailCastAdapter(movieDetailCastItemAdapter)
        movieDetailPhotoItemAdapter = MovieDetailPhotoItemAdapter()
        movieDetailPhotoAdapter = MovieDetailPhotoAdapter(movieDetailPhotoItemAdapter)
        movieDetailRecommendationItemAdapter = MovieDetailRecommendationItemAdapter()
        movieDetailRecommendationAdapter = MovieDetailRecommendationAdapter(movieDetailRecommendationItemAdapter)

        concatAdapter = ConcatAdapter(
            movieDetailStorylineAdapter,
            movieDetailCastAdapter,
            movieDetailPhotoAdapter,
            movieDetailRecommendationAdapter
        )

        binding.apply {
            rvConcat.layoutManager = LinearLayoutManager(context)
            rvConcat.adapter = concatAdapter
        }
    }

    private fun setSwipeToRefresh() {
        binding.swipeRefresh.apply {
            setOnRefreshListener(this@MovieDetailFragment)
            isEnabled = false
            isRefreshing = true
        }
    }

    override fun onRefresh() {
        viewModel.loadData()
    }
}