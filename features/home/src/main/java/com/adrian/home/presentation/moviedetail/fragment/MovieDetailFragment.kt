package com.adrian.home.presentation.moviedetail.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import com.adrian.abstraction.common.state.UIState
import com.adrian.abstraction.common.state.onFailure
import com.adrian.abstraction.common.state.onSuccess
import com.adrian.abstraction.extension.observe
import com.adrian.abstraction.extension.showLongToast
import com.adrian.abstraction.presentation.fragment.BaseFragment
import com.adrian.home.R
import com.adrian.home.data.network.model.moviedetail.MovieDetailResponseJson
import com.adrian.home.databinding.FragmentMovieDetailBinding
import com.adrian.home.presentation.moviedetail.adapter.MovieDetailStorylineAdapter
import com.adrian.home.presentation.moviedetail.viewmodel.MovieDetailViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MovieDetailFragment : BaseFragment(R.layout.fragment_movie_detail) {

    private lateinit var binding: FragmentMovieDetailBinding

    private val viewModel: MovieDetailViewModel by viewModels()

    private lateinit var movieDetailStorylineAdapter: MovieDetailStorylineAdapter
    private lateinit var concatAdapter: ConcatAdapter

    private val movieDetailObserver = Observer<UIState<MovieDetailResponseJson>> { state ->
        state onSuccess {
            movieDetailStorylineAdapter.submitList(data ?: MovieDetailResponseJson())
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
        movieDetailStorylineAdapter = MovieDetailStorylineAdapter()

        concatAdapter = ConcatAdapter(
            movieDetailStorylineAdapter
        )

        binding.apply {
            rvConcat.layoutManager = LinearLayoutManager(context)
            rvConcat.adapter = concatAdapter
        }

        observe(viewModel.movieDetailLiveData, movieDetailObserver)
        viewModel.loadData()
    }
}