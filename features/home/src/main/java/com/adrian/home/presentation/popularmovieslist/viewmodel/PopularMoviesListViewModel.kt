package com.adrian.home.presentation.popularmovieslist.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.adrian.abstraction.common.network.enum.ErrorStatus
import com.adrian.abstraction.common.state.UIState
import com.adrian.abstraction.common.state.onError
import com.adrian.abstraction.common.state.onSuccess
import com.adrian.abstraction.presentation.navigation.NavManager
import com.adrian.abstraction.presentation.viewmodel.BaseViewModel
import com.adrian.home.R
import com.adrian.home.domain.model.popularmovies.PopularMovies
import com.adrian.home.domain.usecase.GetPopularMoviesUseCase
import com.adrian.home.presentation.popularmovieslist.fragment.PopularMoviesListFragmentDirections
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PopularMoviesListViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val application: Application,
    private val getPopularMoviesUseCase: GetPopularMoviesUseCase,
    private val navManager: NavManager
) : BaseViewModel(savedStateHandle) {

    var totalPage: Int? = 0

    val popularMoviesLiveData: MutableLiveData<UIState<List<PopularMovies>>> = MutableLiveData()

    fun getPopularMovies(page: Int) {
        viewModelScope.launch {
            getPopularMoviesUseCase.getPopularMovies(page).also { resultState ->
                popularMoviesLiveData.value = UIState.Loading
                resultState onSuccess {
                    totalPage = data?.totalPages
                    popularMoviesLiveData.value = UIState.Success(data?.results)
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

    fun navigateToMovieDetail(movieId: Int) {
        val navDirections = PopularMoviesListFragmentDirections.actionPopularMoviesToMovieDetail(movieId)
        navManager.navigate(navDirections)
    }
}