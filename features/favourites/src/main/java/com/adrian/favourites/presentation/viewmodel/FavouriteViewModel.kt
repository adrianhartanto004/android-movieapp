package com.adrian.favourites.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.adrian.abstraction.common.domain.model.FavouriteMovie
import com.adrian.abstraction.common.network.enum.ErrorStatus
import com.adrian.abstraction.common.state.UIState
import com.adrian.abstraction.common.state.onError
import com.adrian.abstraction.common.state.onSuccess
import com.adrian.abstraction.presentation.viewmodel.BaseViewModel
import com.adrian.favourites.R
import com.adrian.favourites.domain.usecase.GetFavouriteMoviesUseCase
import kotlinx.coroutines.launch

class FavouriteViewModel(
    private val application: Application,
    private val getFavouriteMoviesUseCase: GetFavouriteMoviesUseCase
) : BaseViewModel() {

    val favouriteMoviesLiveData: MutableLiveData<UIState<List<FavouriteMovie>>> = MutableLiveData()

    fun getFavouriteMovies() {
        viewModelScope.launch {
            getFavouriteMoviesUseCase.getFavouriteMovies().also { resultState ->
                favouriteMoviesLiveData.value = UIState.Loading
                resultState onSuccess {
                    favouriteMoviesLiveData.value = UIState.Success(data)
                }
                resultState onError {
                    favouriteMoviesLiveData.value =
                        UIState.Failure(
                            ErrorStatus.APPLICATION_ERROR,
                            application.getString(R.string.default_application_error)
                        )
                }
            }
        }
    }

    fun loadData() {
        getFavouriteMovies()
    }
}