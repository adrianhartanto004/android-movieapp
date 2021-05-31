package com.adrian.home.presentation.home.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.adrian.abstraction.common.network.enum.ErrorStatus
import com.adrian.abstraction.common.state.UIState
import com.adrian.abstraction.common.state.onError
import com.adrian.abstraction.common.state.onSuccess
import com.adrian.abstraction.presentation.viewmodel.BaseViewModel
import com.adrian.home.R
import com.adrian.home.domain.model.genre.Genre
import com.adrian.home.domain.model.nowplayingmovies.NowPlayingMovies
import com.adrian.home.domain.model.popularmovies.PopularMovies
import com.adrian.home.domain.usecase.GetGenresUseCase
import com.adrian.home.domain.usecase.GetNowPlayingMoviesUseCase
import com.adrian.home.domain.usecase.GetPopularMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val application: Application,
    private val getPopularMoviesUseCase: GetPopularMoviesUseCase,
    private val getNowPlayingMoviesUseCase: GetNowPlayingMoviesUseCase,
    private val getGenresUseCase: GetGenresUseCase
) : BaseViewModel() {

    val popularMoviesLiveData: MutableLiveData<UIState<List<PopularMovies>>> = MutableLiveData()
    val nowPlayingMoviesLiveData: MutableLiveData<UIState<List<NowPlayingMovies>>> =
        MutableLiveData()
    val genresLiveData: MutableLiveData<UIState<List<Genre>>> = MutableLiveData()

    fun getPopularMovies(page: Int) {
        viewModelScope.launch {
            getPopularMoviesUseCase.getPopularMovies(page).also { resultState ->
                popularMoviesLiveData.value = UIState.Loading
                resultState onSuccess {
                    popularMoviesLiveData.value = UIState.Success(data.results)
                }
                resultState onError {
                    popularMoviesLiveData.value =
                        UIState.Failure(
                            ErrorStatus.APPLICATION_ERROR,
                            application.getString(R.string.default_application_error)
                        )
                }
            }
        }
    }

    fun getNowPlayingMovies(page: Int) {
        viewModelScope.launch {
            getNowPlayingMoviesUseCase.getNowPlayingMovies(page).also { resultState ->
                nowPlayingMoviesLiveData.value = UIState.Loading
                resultState onSuccess {
                    nowPlayingMoviesLiveData.value = UIState.Success(data.results)
                }
                resultState onError {
                    nowPlayingMoviesLiveData.value =
                        UIState.Failure(
                            ErrorStatus.APPLICATION_ERROR,
                            application.getString(R.string.default_application_error)
                        )
                }
            }
        }
    }

    fun getGenres() {
        viewModelScope.launch {
            getGenresUseCase.getGenres().also { resultState ->
                genresLiveData.value = UIState.Loading
                resultState onSuccess {
                    genresLiveData.value = UIState.Success(data)
                }
                resultState onError {
                    genresLiveData.value =
                        UIState.Failure(
                            ErrorStatus.APPLICATION_ERROR,
                            application.getString(R.string.default_application_error)
                        )
                }
            }
        }
    }

    fun loadData() {
        getPopularMovies(1)
        getNowPlayingMovies(1)
        getGenres()
    }
}