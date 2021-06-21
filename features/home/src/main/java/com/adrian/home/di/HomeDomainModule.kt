package com.adrian.home.di

import com.adrian.home.domain.usecase.*
import org.koin.dsl.module

val homeDomainModule = module {
    single { AddFavouriteMovieUseCase(get()) }
    single { GetAuthorReviewsUseCase(get()) }
    single { GetGenresUseCase(get()) }
    single { GetIsFavouriteMovieExistUseCase(get()) }
    single { GetMovieCreditsUseCase(get()) }
    single { GetMovieDetailUseCase(get()) }
    single { GetMoviePhotosUseCase(get()) }
    single { GetNowPlayingMoviesUseCase(get()) }
    single { GetPopularMoviesUseCase(get()) }
    single { GetRecommendedMoviesUseCase(get()) }
}