package com.adrian.home.presentation.home.viewmodel

import android.app.Application
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.adrian.abstraction.common.network.enum.ErrorStatus
import com.adrian.abstraction.common.state.UIState
import com.adrian.abstraction.presentation.viewmodel.BaseViewModel
import com.adrian.home.R
import com.adrian.home.domain.model.genre.Genre
import com.adrian.home.domain.model.nowplayingmovies.NowPlayingMovies
import com.adrian.home.domain.model.popularmovies.PopularMovies
import com.adrian.home.domain.usecase.GetGenresUseCase
import com.adrian.home.domain.usecase.GetNowPlayingMoviesUseCase
import com.adrian.home.domain.usecase.GetPopularMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
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

    val popularMoviesState: MutableStateFlow<UIState<List<PopularMovies>>> =
        MutableStateFlow(UIState.Loading)
    val nowPlayingMoviesState: MutableStateFlow<UIState<List<NowPlayingMovies>>> =
        MutableStateFlow(UIState.Loading)
    val genresState: MutableStateFlow<UIState<List<Genre>>> = MutableStateFlow(UIState.Loading)

    fun getPopularMovies(page: Int) {
        viewModelScope.launch {
            getPopularMoviesUseCase.getPopularMovies(page)
                .catch {
                    popularMoviesState.value =
                        UIState.Failure(
                            ErrorStatus.APPLICATION_ERROR,
                            application.getString(R.string.default_application_error)
                        )
                }
                .collect {
                    popularMoviesState.value = UIState.Success(it.results)
                }
        }
    }

    fun getNowPlayingMovies(page: Int) {
        viewModelScope.launch {
            getNowPlayingMoviesUseCase.getNowPlayingMovies(page)
                .catch {
                    nowPlayingMoviesState.value =
                        UIState.Failure(
                            ErrorStatus.APPLICATION_ERROR,
                            application.getString(R.string.default_application_error)
                        )
                }
                .collect {
                    nowPlayingMoviesState.value = UIState.Success(it.results)
                }
        }
    }

    fun getGenres() {
        viewModelScope.launch {
            getGenresUseCase.getGenres()
                .catch {
                    genresState.value =
                        UIState.Failure(
                            ErrorStatus.APPLICATION_ERROR,
                            application.getString(R.string.default_application_error)
                        )
                }
                .collect {
                    genresState.value = UIState.Success(it)
                }
        }
    }

    fun loadData() {
        getPopularMovies(1)
        getNowPlayingMovies(1)
        getGenres()
    }
}
