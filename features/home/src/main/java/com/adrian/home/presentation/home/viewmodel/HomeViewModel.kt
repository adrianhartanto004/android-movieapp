package com.adrian.home.presentation.home.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
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
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val application: Application,
    private val getPopularMoviesUseCase: GetPopularMoviesUseCase,
    private val getNowPlayingMoviesUseCase: GetNowPlayingMoviesUseCase,
    private val getGenresUseCase: GetGenresUseCase
) : BaseViewModel(savedStateHandle) {

    val popularMoviesLiveData: MutableLiveData<UIState<List<PopularMovies>>> = MutableLiveData()
    val nowPlayingMoviesLiveData: MutableLiveData<UIState<List<NowPlayingMovies>>> =
        MutableLiveData()
    val genresLiveData: MutableLiveData<UIState<List<Genre>>> = MutableLiveData()

    fun getPopularMovies(page: Int) {
        viewModelScope.launch {
            getPopularMoviesUseCase.getPopularMovies(page)
                .onStart {
                    popularMoviesLiveData.value = UIState.Loading
                }
                .catch {
                    popularMoviesLiveData.value =
                        UIState.Failure(
                            ErrorStatus.APPLICATION_ERROR,
                            application.getString(R.string.default_application_error)
                        )
                }
                .collect {
                    popularMoviesLiveData.value = UIState.Success(it.results)
                }
        }
    }

    fun getNowPlayingMovies(page: Int) {
        viewModelScope.launch {
            getNowPlayingMoviesUseCase.getNowPlayingMovies(page)
                .onStart {
                    nowPlayingMoviesLiveData.value = UIState.Loading
                }
                .catch {
                    nowPlayingMoviesLiveData.value =
                        UIState.Failure(
                            ErrorStatus.APPLICATION_ERROR,
                            application.getString(R.string.default_application_error)
                        )
                }
                .collect {
                    nowPlayingMoviesLiveData.value = UIState.Success(it.results)
                }
        }
    }

    fun getGenres() {
        viewModelScope.launch {
            getGenresUseCase.getGenres()
                .onStart {
                    genresLiveData.value = UIState.Loading
                }
                .catch {
                    genresLiveData.value =
                        UIState.Failure(
                            ErrorStatus.APPLICATION_ERROR,
                            application.getString(R.string.default_application_error)
                        )
                }
                .collect {
                    genresLiveData.value = UIState.Success(it)
                }
        }
    }

    fun loadData() {
        getPopularMovies(1)
        getNowPlayingMovies(1)
        getGenres()
    }
}
