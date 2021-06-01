package com.adrian.home.presentation.moviedetail.viewmodel

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
import com.adrian.home.data.network.model.moviedetail.MovieDetailResponseJson
import com.adrian.home.domain.usecase.GetMovieDetailUseCase
import com.adrian.home.presentation.moviedetail.fragment.MovieDetailFragmentArgs
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val application: Application,
    private val getMovieDetailUseCase: GetMovieDetailUseCase
) : BaseViewModel(savedStateHandle) {

    val movieDetailLiveData: MutableLiveData<UIState<MovieDetailResponseJson>> = MutableLiveData()

    private val args: MovieDetailFragmentArgs by navArgs()

    fun getMovieDetail() {
        viewModelScope.launch {
            getMovieDetailUseCase.getMovieDetail(args.movieId).also { resultState ->
                movieDetailLiveData.value = UIState.Loading
                resultState onSuccess {
                    movieDetailLiveData.value = UIState.Success(data)
                }
                resultState onError {
                    movieDetailLiveData.value =
                        UIState.Failure(
                            ErrorStatus.APPLICATION_ERROR,
                            application.getString(R.string.default_application_error)
                        )
                }
            }
        }
    }

    fun loadData() {
        getMovieDetail()
    }
}