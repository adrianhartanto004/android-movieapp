package com.adrian.home.presentation.home.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import com.adrian.abstraction.common.state.onFailure
import com.adrian.abstraction.common.state.onSuccess
import com.adrian.abstraction.extension.showLongToast
import com.adrian.abstraction.presentation.fragment.BaseFragment
import com.adrian.home.R
import com.adrian.home.databinding.FragmentHomeBinding
import com.adrian.home.presentation.home.adapter.NowPlayingMoviesAdapter
import com.adrian.home.presentation.home.adapter.NowPlayingMoviesItemAdapter
import com.adrian.home.presentation.home.adapter.PopularMoviesAdapter
import com.adrian.home.presentation.home.adapter.PopularMoviesItemAdapter
import com.adrian.home.presentation.home.viewmodel.HomeViewModel
import kotlinx.coroutines.flow.collect

class HomeFragment : BaseFragment(R.layout.fragment_home) {

    private lateinit var binding: FragmentHomeBinding

    private val viewModel: HomeViewModel by viewModels()

    private lateinit var popularMoviesAdapter: PopularMoviesAdapter
    private lateinit var popularMoviesItemAdapter: PopularMoviesItemAdapter
    private lateinit var nowPlayingMoviesAdapter: NowPlayingMoviesAdapter
    private lateinit var nowPlayingMoviesItemAdapter: NowPlayingMoviesItemAdapter
    private lateinit var concatAdapter: ConcatAdapter

    private fun observePopularMovies() = lifecycleScope.launchWhenStarted {
        viewModel.popularMoviesState.collect { state ->
            state onSuccess {
                popularMoviesItemAdapter.submitList(data ?: listOf())
            }
            state onFailure {
                showLongToast(message)
            }
        }
    }

    private fun observeNowPlayingMovies() = lifecycleScope.launchWhenStarted {
        viewModel.nowPlayingMoviesState.collect { state ->
            state onSuccess {
                nowPlayingMoviesItemAdapter.submitList(data ?: listOf())
            }
            state onFailure {
                showLongToast(message)
            }
        }
    }

    private fun observeGenres() = lifecycleScope.launchWhenStarted {
        viewModel.genresState.collect { state ->
            state onSuccess {
                nowPlayingMoviesItemAdapter.setGenres(data ?: listOf())
            }
            state onFailure {
            }
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
        if (!viewModel.apiDataReceived) {
            viewModel.loadData()
            viewModel.apiDataReceived = true
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

        observePopularMovies()
        observeNowPlayingMovies()
        observeGenres()

        popularMoviesAdapter.setOnShowMoreClickListener {
            val navDirections = HomeFragmentDirections.actionPopularMoviesShowAll()
            findNavController().navigate(navDirections)
        }

        popularMoviesItemAdapter.setOnItemClickedListener {
            val navDirections = HomeFragmentDirections.actionPopularMoviesToMovieDetail(it.id)
            findNavController().navigate(navDirections)
        }

        nowPlayingMoviesItemAdapter.setOnItemClickedListener {
            val navDirections = HomeFragmentDirections.actionPopularMoviesToMovieDetail(it.id)
            findNavController().navigate(navDirections)
        }
    }
}
