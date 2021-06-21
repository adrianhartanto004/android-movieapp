package com.adrian.home.presentation.home.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import com.adrian.abstraction.common.state.UIState
import com.adrian.abstraction.common.state.onFailure
import com.adrian.abstraction.common.state.onSuccess
import com.adrian.abstraction.extension.observe
import com.adrian.abstraction.extension.showLongToast
import com.adrian.abstraction.presentation.fragment.BaseFragment
import com.adrian.home.R
import com.adrian.home.databinding.FragmentHomeBinding
import com.adrian.home.domain.model.genre.Genre
import com.adrian.home.domain.model.nowplayingmovies.NowPlayingMovies
import com.adrian.home.domain.model.popularmovies.PopularMovies
import com.adrian.home.presentation.home.adapter.NowPlayingMoviesAdapter
import com.adrian.home.presentation.home.adapter.NowPlayingMoviesItemAdapter
import com.adrian.home.presentation.home.adapter.PopularMoviesAdapter
import com.adrian.home.presentation.home.adapter.PopularMoviesItemAdapter
import com.adrian.home.presentation.home.viewmodel.HomeViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : BaseFragment(R.layout.fragment_home) {

    private lateinit var binding: FragmentHomeBinding

    private val homeViewModel: HomeViewModel by viewModel()

    private lateinit var popularMoviesAdapter: PopularMoviesAdapter
    private lateinit var popularMoviesItemAdapter: PopularMoviesItemAdapter
    private lateinit var nowPlayingMoviesAdapter: NowPlayingMoviesAdapter
    private lateinit var nowPlayingMoviesItemAdapter: NowPlayingMoviesItemAdapter
    private lateinit var concatAdapter: ConcatAdapter

    private val popularMoviesObserver = Observer<UIState<List<PopularMovies>>> { state ->
        state onSuccess {
            popularMoviesItemAdapter.submitList(data ?: listOf())
        }
        state onFailure {
            showLongToast(message)
        }
    }

    private val nowPlayingMoviesObserver = Observer<UIState<List<NowPlayingMovies>>> { state ->
        state onSuccess {
            nowPlayingMoviesItemAdapter.submitList(data ?: listOf())
        }
        state onFailure {
            showLongToast(message)
        }
    }

    private val genresObserver = Observer<UIState<List<Genre>>> { state ->
        state onSuccess {
            nowPlayingMoviesItemAdapter.setGenres(data ?: listOf())
        }
        state onFailure {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        if (!homeViewModel.apiDataReceived) {
            homeViewModel.loadData()
            homeViewModel.apiDataReceived = true
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        popularMoviesItemAdapter = PopularMoviesItemAdapter()
        popularMoviesAdapter = PopularMoviesAdapter(popularMoviesItemAdapter)

        nowPlayingMoviesItemAdapter = NowPlayingMoviesItemAdapter()
        nowPlayingMoviesAdapter = NowPlayingMoviesAdapter(nowPlayingMoviesItemAdapter)

        concatAdapter = ConcatAdapter(
            popularMoviesAdapter,
            nowPlayingMoviesAdapter
        )
        binding.rvConcat.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = concatAdapter
        }

        observe(homeViewModel.popularMoviesLiveData, popularMoviesObserver)
        observe(homeViewModel.nowPlayingMoviesLiveData, nowPlayingMoviesObserver)
        observe(homeViewModel.genresLiveData, genresObserver)

        popularMoviesAdapter.setOnShowMoreClickListener {
            val navDirections = HomeFragmentDirections.actionPopularMoviesShowAll()
            findNavController().navigate(navDirections)
        }

        popularMoviesItemAdapter.setOnItemClickedListener {
            val navDirections = HomeFragmentDirections.actionPopularMoviesToMovieDetail(it.id)
            findNavController().navigate(navDirections)
        }
    }
}
