package com.adrian.home.presentation.popularmovieslist.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.adrian.abstraction.common.network.enum.ErrorStatus
import com.adrian.abstraction.common.state.UIState
import com.adrian.abstraction.presentation.viewmodel.BaseViewModel
import com.adrian.home.R
import com.adrian.home.domain.model.popularmovies.PopularMovies
import com.adrian.home.domain.usecase.GetPopularMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PopularMoviesListViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val application: Application,
    private val getPopularMoviesUseCase: GetPopularMoviesUseCase
) : BaseViewModel(savedStateHandle) {

    var totalPage: Int? = 0

    val popularMoviesLiveData: MutableLiveData<UIState<List<PopularMovies>>> = MutableLiveData()

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

}
