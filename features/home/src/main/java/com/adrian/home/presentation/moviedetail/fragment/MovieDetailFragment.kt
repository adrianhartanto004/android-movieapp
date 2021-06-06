package com.adrian.home.presentation.moviedetail.fragment

import android.os.Bundle
import android.view.*
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
import com.adrian.home.data.network.model.authorreview.AuthorReviewListJson
import com.adrian.home.data.network.model.moviecredits.MovieCreditListJson
import com.adrian.home.data.network.model.moviedetail.MovieDetailResponseJson
import com.adrian.home.data.network.model.moviedetail.toFavouriteMovie
import com.adrian.home.data.network.model.moviephoto.Backdrop
import com.adrian.home.databinding.FragmentMovieDetailBinding
import com.adrian.home.domain.model.recommendedmovies.RecommendedMovies
import com.adrian.home.presentation.moviedetail.adapter.*
import com.adrian.home.presentation.moviedetail.viewmodel.MovieDetailViewModel

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
    private lateinit var movieDetailAuthorReviewAdapter: MovieDetailAuthorReviewAdapter
    private lateinit var movieDetailAuthorReviewItemAdapter: MovieDetailAuthorReviewItemAdapter
    private lateinit var concatAdapter: ConcatAdapter

    private var isLiked = false

    private val movieDetailObserver = Observer<UIState<MovieDetailResponseJson>> { state ->
        state onLoading {
            binding.swipeRefresh.isRefreshing = true
        }
        state onSuccess {
            if (data != null) {
                movieDetailStorylineAdapter.submitList(data ?: MovieDetailResponseJson())
//                viewModel.isFavouriteMovieExist(viewModel.currentFavouriteMovie?.id ?: 0)
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

    private val authorReviewsObserver = Observer<UIState<AuthorReviewListJson>> { state ->
        state onLoading {
        }
        state onSuccess {
            movieDetailAuthorReviewAdapter.submitList(data)
            movieDetailAuthorReviewItemAdapter.submitList(data?.authorReviews ?: listOf())
        }
        state onFailure {
            showLongToast(message)
        }
    }

    private val isFavouriteMovieExistObserver = Observer<UIState<Boolean>> { state ->
        state onLoading {
        }
        state onSuccess {
            isLiked = data!!
            activity?.invalidateOptionsMenu()
        }
        state onFailure {
            showLongToast(message)
        }
    }

    private val addFavouriteMovieObserver = Observer<UIState<Boolean>> { state ->
        state onLoading {
        }
        state onSuccess {
            viewModel.isFavouriteMovieExist(viewModel.currentFavouriteMovie?.id ?: 0)
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
        observe(viewModel.authorReviewsLiveData, authorReviewsObserver)
        observe(viewModel.isFavouriteMovieExistLiveData, isFavouriteMovieExistObserver)
        observe(viewModel.addFavouriteMovieLiveData, addFavouriteMovieObserver)
        setHasOptionsMenu(true)
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
        movieDetailRecommendationAdapter =
            MovieDetailRecommendationAdapter(movieDetailRecommendationItemAdapter)
        movieDetailAuthorReviewAdapter = MovieDetailAuthorReviewAdapter()
        movieDetailAuthorReviewItemAdapter = MovieDetailAuthorReviewItemAdapter()

        concatAdapter = ConcatAdapter(
            movieDetailStorylineAdapter,
            movieDetailCastAdapter,
            movieDetailPhotoAdapter,
            movieDetailRecommendationAdapter,
            movieDetailAuthorReviewAdapter,
            movieDetailAuthorReviewItemAdapter
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

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.movie_detail_menu, menu)
        if (isLiked) {
            menu.findItem(R.id.action_like_movie).setIcon(R.drawable.ic_favourite_red)
        } else {
            menu.findItem(R.id.action_like_movie).setIcon(R.drawable.ic_favourite_border_red)
        }
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_like_movie -> {
                viewModel.addFavouriteMovie(viewModel.currentFavouriteMovie?.toFavouriteMovie()!!)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onRefresh() {
        viewModel.loadData()
    }
}