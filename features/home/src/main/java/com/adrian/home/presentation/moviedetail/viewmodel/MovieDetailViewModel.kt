package com.adrian.home.presentation.moviedetail.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.adrian.abstraction.common.domain.model.FavouriteMovie
import com.adrian.abstraction.common.network.enum.ErrorStatus
import com.adrian.abstraction.common.state.UIState
import com.adrian.abstraction.common.state.onError
import com.adrian.abstraction.common.state.onSuccess
import com.adrian.abstraction.presentation.viewmodel.BaseViewModel
import com.adrian.home.R
import com.adrian.home.data.network.model.authorreview.AuthorReviewListJson
import com.adrian.home.data.network.model.moviecredits.MovieCreditListJson
import com.adrian.home.data.network.model.moviedetail.MovieDetailResponseJson
import com.adrian.home.data.network.model.moviephoto.Backdrop
import com.adrian.home.domain.model.recommendedmovies.RecommendedMovies
import com.adrian.home.domain.usecase.*
import kotlinx.coroutines.launch

class MovieDetailViewModel(
    private val application: Application,
    private val getMovieDetailUseCase: GetMovieDetailUseCase,
    private val getMovieCreditsUseCase: GetMovieCreditsUseCase,
    private val getMoviePhotosUseCase: GetMoviePhotosUseCase,
    private val getRecommendedMoviesUseCase: GetRecommendedMoviesUseCase,
    private val getAuthorReviewsUseCase: GetAuthorReviewsUseCase,
    private val addFavouriteMovieUseCase: AddFavouriteMovieUseCase,
    private val getIsFavouriteMovieExistUseCase: GetIsFavouriteMovieExistUseCase
) : BaseViewModel() {

    val movieDetailLiveData: MutableLiveData<UIState<MovieDetailResponseJson>> = MutableLiveData()
    val movieCreditsLiveData: MutableLiveData<UIState<MovieCreditListJson>> = MutableLiveData()
    val moviePhotosLiveData: MutableLiveData<UIState<List<Backdrop>>> = MutableLiveData()
    val recommendedMoviesLiveData: MutableLiveData<UIState<List<RecommendedMovies>>> =
        MutableLiveData()
    val authorReviewsLiveData: MutableLiveData<UIState<AuthorReviewListJson>> = MutableLiveData()
    val addFavouriteMovieLiveData: MutableLiveData<UIState<Boolean>> = MutableLiveData()
    val isFavouriteMovieExistLiveData: MutableLiveData<UIState<Boolean>> = MutableLiveData()

    var currentFavouriteMovie: MovieDetailResponseJson? = MovieDetailResponseJson()

    private fun getMovieDetail(movieId: Int) {
        viewModelScope.launch {
            getMovieDetailUseCase.getMovieDetail(movieId).also { resultState ->
                movieDetailLiveData.value = UIState.Loading
                resultState onSuccess {
                    currentFavouriteMovie = data
                    movieDetailLiveData.value = UIState.Success(data)
                    isFavouriteMovieExist(data?.id ?: 0)
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

    private fun getMovieCredits(movieId: Int) {
        viewModelScope.launch {
            getMovieCreditsUseCase.getMovieCredits(movieId).also { resultState ->
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

    private fun getMoviePhotos(movieId: Int) {
        viewModelScope.launch {
            getMoviePhotosUseCase.getMoviePhotos(movieId).also { resultState ->
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

    fun getRecommendedMovies(movieId: Int, page: Int) {
        viewModelScope.launch {
            getRecommendedMoviesUseCase.getRecommendedMovies(movieId, page)
                .also { resultState ->
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

    fun getAuthorReviews(movieId: Int, page: Int) {
        viewModelScope.launch {
            getAuthorReviewsUseCase.getAuthorReviews(movieId, page).also { resultState ->
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


    fun isFavouriteMovieExist(movieId: Int) {
        viewModelScope.launch {
            getIsFavouriteMovieExistUseCase.getIsFavouriteMovieExist(movieId).also { resultState ->
                isFavouriteMovieExistLiveData.value = UIState.Loading
                resultState onSuccess {
                    isFavouriteMovieExistLiveData.value = UIState.Success(data)
                }
                resultState onError {
                    isFavouriteMovieExistLiveData.value =
                        UIState.Failure(
                            ErrorStatus.APPLICATION_ERROR,
                            application.getString(R.string.default_application_error)
                        )
                }
            }
        }
    }

    fun addFavouriteMovie(favouriteMovie: FavouriteMovie) {
        viewModelScope.launch {
            addFavouriteMovieUseCase.addFavouriteMovie(favouriteMovie).also { resultState ->
                addFavouriteMovieLiveData.value = UIState.Loading
                resultState onSuccess {
                    addFavouriteMovieLiveData.value = UIState.Success(data)
                }
                resultState onError {
                    addFavouriteMovieLiveData.value =
                        UIState.Failure(
                            ErrorStatus.APPLICATION_ERROR,
                            application.getString(R.string.default_application_error)
                        )
                }
            }
        }
    }

    fun loadData(movieId: Int) {
        getMovieDetail(movieId)
        getMovieCredits(movieId)
        getMoviePhotos(movieId)
        getRecommendedMovies(movieId, 1)
        getAuthorReviews(movieId, 1)
    }
}