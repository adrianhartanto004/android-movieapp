package com.adrian.movieapp.presentation.movielist.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.adrian.movieapp.data.network.model.Result
import com.adrian.movieapp.data.repository.ApiResult
import com.adrian.movieapp.data.repository.MovieRepository

class MovieListViewModel(
    private val movieRepository: MovieRepository
) : ViewModel() {

    val movieLiveData: MutableLiveData<List<Result>> = MutableLiveData()

    fun getPopularMovies() {
        movieRepository.getPopularMovies { result ->
            when (result) {
                is ApiResult.Success<List<Result>> -> {
                    movieLiveData.value = result.data
                }
                else -> {

                }
            }
        }
    }
}