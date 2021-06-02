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
import com.adrian.home.data.network.model.authorreview.AuthorReview
import com.adrian.home.data.network.model.authorreview.AuthorReviewListJson
import com.adrian.home.data.network.model.moviecredits.MovieCreditListJson
import com.adrian.home.data.network.model.moviedetail.MovieDetailResponseJson
import com.adrian.home.data.network.model.moviephoto.Backdrop
import com.adrian.home.domain.model.recommendedmovies.RecommendedMovies
import com.adrian.home.domain.usecase.*
import com.adrian.home.presentation.moviedetail.fragment.MovieDetailFragmentArgs
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val application: Application,
    private val getMovieDetailUseCase: GetMovieDetailUseCase,
    private val getMovieCreditsUseCase: GetMovieCreditsUseCase,
    private val getMoviePhotosUseCase: GetMoviePhotosUseCase,
    private val getRecommendedMoviesUseCase: GetRecommendedMoviesUseCase,
    private val getAuthorReviewsUseCase: GetAuthorReviewsUseCase
) : BaseViewModel(savedStateHandle) {

    val movieDetailLiveData: MutableLiveData<UIState<MovieDetailResponseJson>> = MutableLiveData()
    val movieCreditsLiveData: MutableLiveData<UIState<MovieCreditListJson>> = MutableLiveData()
    val moviePhotosLiveData: MutableLiveData<UIState<List<Backdrop>>> = MutableLiveData()
    val recommendedMoviesLiveData: MutableLiveData<UIState<List<RecommendedMovies>>> = MutableLiveData()
    val authorReviewsLiveData: MutableLiveData<UIState<AuthorReviewListJson>> = MutableLiveData()

    private val args: MovieDetailFragmentArgs by navArgs()

    private fun getMovieDetail() {
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

    private fun getMovieCredits() {
        viewModelScope.launch {
            getMovieCreditsUseCase.getMovieCredits(args.movieId).also { resultState ->
                movieCreditsLiveData.value = UIState.Loading
                resultState onSuccess {
                    movieCreditsLiveData.value = UIState.Success(data)
                }
                resultState onError {
                    movieCreditsLiveData.value =
                        UIState.Failure(
                            ErrorStatus.APPLICATION_ERROR,
                            application.getString(R.string.default_application_error)
                        )
                }
            }
        }
    }

    private fun getMoviePhotos() {
        viewModelScope.launch {
            getMoviePhotosUseCase.getMoviePhotos(args.movieId).also { resultState ->
                moviePhotosLiveData.value = UIState.Loading
                resultState onSuccess {
                    moviePhotosLiveData.value = UIState.Success(data?.backdrops)
                }
                resultState onError {
                    moviePhotosLiveData.value =
                        UIState.Failure(
                            ErrorStatus.APPLICATION_ERROR,
                            application.getString(R.string.default_application_error)
                        )
                }
            }
        }
    }

    fun getRecommendedMovies(page: Int) {
        viewModelScope.launch {
            getRecommendedMoviesUseCase.getRecommendedMovies(args.movieId, page).also { resultState ->
                recommendedMoviesLiveData.value = UIState.Loading
                resultState onSuccess {
                    recommendedMoviesLiveData.value = UIState.Success(data?.results)
                }
                resultState onError {
                    recommendedMoviesLiveData.value =
                        UIState.Failure(
                            ErrorStatus.APPLICATION_ERROR,
                            application.getString(R.string.default_application_error)
                        )
                }
            }
        }
    }

    fun getAuthorReviews(page: Int) {
        viewModelScope.launch {
            getAuthorReviewsUseCase.getAuthorReviews(args.movieId, page).also { resultState ->
                authorReviewsLiveData.value = UIState.Loading
                resultState onSuccess {
                    authorReviewsLiveData.value = UIState.Success(data)
                }
                resultState onError {
                    authorReviewsLiveData.value =
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
        getMovieCredits()
        getMoviePhotos()
        getRecommendedMovies(1)
        getAuthorReviews(1)
    }
}