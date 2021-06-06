package com.adrian.favourites.presentation.fragment

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.adrian.abstraction.common.domain.model.FavouriteMovie
import com.adrian.abstraction.common.state.UIState
import com.adrian.abstraction.common.state.onFailure
import com.adrian.abstraction.common.state.onSuccess
import com.adrian.abstraction.extension.observe
import com.adrian.abstraction.extension.showLongToast
import com.adrian.abstraction.presentation.fragment.BaseFragment
import com.adrian.favourites.R
import com.adrian.favourites.databinding.FragmentFavouriteBinding
import com.adrian.favourites.presentation.adapter.FavouriteMoviesAdapter
import com.adrian.favourites.presentation.viewmodel.FavouriteViewModel

class FavouriteFragment : BaseFragment(R.layout.fragment_favourite) {

    private lateinit var binding: FragmentFavouriteBinding

    private val viewModel: FavouriteViewModel by viewModels()

    private lateinit var favouriteMoviesAdapter: FavouriteMoviesAdapter

    private val favouriteMoviesObserver = Observer<UIState<List<FavouriteMovie>>> { state ->
        state onSuccess {
            favouriteMoviesAdapter.submitList(data ?: listOf())
            if (favouriteMoviesAdapter.itemCount == 0) {
                showLongToast("Please add some movies to your favourite")
            }
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
        binding = FragmentFavouriteBinding.inflate(inflater, container, false)
        super.onCreate(savedInstanceState)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        favouriteMoviesAdapter = FavouriteMoviesAdapter()
        binding.apply {
            rvList.layoutManager = LinearLayoutManager(context)
            rvList.adapter = favouriteMoviesAdapter
        }

        observe(viewModel.favouriteMoviesLiveData, favouriteMoviesObserver)
        viewModel.loadData()

        favouriteMoviesAdapter.setOnItemClickedListener {
            val uri = Uri.parse("movieApp://featureMovieDetail/?movieId=${it.id}")
            findNavController().navigate(uri)
        }
    }
}
