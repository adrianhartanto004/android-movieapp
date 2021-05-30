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
import com.adrian.home.domain.model.PopularMovies
import com.adrian.home.domain.usecase.GetPopularMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val application: Application,
    private val getPopularMoviesUseCase: GetPopularMoviesUseCase
) : BaseViewModel() {

    val popularMoviesLiveData: MutableLiveData<UIState<List<PopularMovies>>> = MutableLiveData()

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
}