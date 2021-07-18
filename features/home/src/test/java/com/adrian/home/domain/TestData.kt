package com.adrian.home.domain

import com.adrian.home.data.network.model.genre.GenreJson
import com.adrian.home.data.network.model.genre.GenreListJson
import com.adrian.home.data.network.model.nowplayingmovies.NowPlayingMoviesJson
import com.adrian.home.data.network.model.nowplayingmovies.NowPlayingMoviesListJson
import com.adrian.home.data.network.model.popularmovies.PopularMovieListJson
import com.adrian.home.data.network.model.popularmovies.PopularMoviesJson
import com.adrian.home.domain.model.genre.Genre
import com.adrian.home.domain.model.nowplayingmovies.NowPlayingMovies
import com.adrian.home.domain.model.nowplayingmovies.NowPlayingMoviesList
import com.adrian.home.domain.model.popularmovies.PopularMovies
import com.adrian.home.domain.model.popularmovies.PopularMoviesList
import kotlinx.coroutines.flow.flow

internal object TestData {
    val popularMoviesListData = listOf(
        PopularMovies(
            false,
            1,
            "en",
            "Avengers",
            "overview",
            9.0,
            "posterPath",
            "2021-01-01",
            "title",
            false,
            9.3,
            1300
        ),
        PopularMovies(
            false,
            2,
            "en",
            "Avengers",
            "overview",
            9.0,
            "posterPath",
            "2021-01-01",
            "title",
            false,
            9.3,
            1300
        )
    )

    val popularMoviesListDataJson = listOf(
        PopularMoviesJson(
            false,
            1,
            "en",
            "Avengers",
            "overview",
            9.0,
            "posterPath",
            "2021-01-01",
            "title",
            false,
            9.3,
            1300
        ),
        PopularMoviesJson(
            false,
            1,
            "en",
            "Avengers",
            "overview",
            9.0,
            "posterPath",
            "2021-01-01",
            "title",
            false,
            9.3,
            1300
        )
    )
    val popularMoviesList = flow {
        emit(
            PopularMoviesList(1, popularMoviesListData, 1, 2)
        )
    }

    val popularMovieListJson = PopularMovieListJson(1, popularMoviesListDataJson, 1, 2)

    val genreIds = listOf(27, 30, 50)
    val nowPlayingMoviesListData = listOf(
        NowPlayingMovies(
            false,
            genreIds,
            3,
            "en",
            "Avengers",
            "overview",
            9.0,
            "2021-01-01",
            "title",
            "Avengers",
            false,
            9.3,
            1300
        ),
        NowPlayingMovies(
            false,
            genreIds,
            3,
            "en",
            "Avengers",
            "overview",
            9.0,
            "2021-01-01",
            "title",
            "Avengers",
            false,
            9.3,
            1300
        )
    )
    val nowPlayingMoviesListDataJson = listOf(
        NowPlayingMoviesJson(
            false,
            genreIds,
            3,
            "en",
            "Avengers",
            "overview",
            9.0,
            "2021-01-01",
            "title",
            "Avengers",
            false,
            9.3,
            1300
        ),
        NowPlayingMoviesJson(
            false,
            genreIds,
            3,
            "en",
            "Avengers",
            "overview",
            9.0,
            "2021-01-01",
            "title",
            "Avengers",
            false,
            9.3,
            1300
        )
    )

    val nowPlayingMoviesList = flow {
        emit(
            NowPlayingMoviesList(1, nowPlayingMoviesListData, 1, 2)
        )
    }
    val nowPlayingMoviesListJson = NowPlayingMoviesListJson(1, nowPlayingMoviesListDataJson, 1, 2)

    val genresListFlowData = flow {
        emit(
            listOf(
                Genre(1, "Horror"),
                Genre(2, "Action"),
                Genre(3, "Comedy")
            )
        )
    }

    val genresListData =
        listOf(
            Genre(1, "Horror"),
            Genre(2, "Action"),
            Genre(3, "Comedy")
        )

    val genreListJson = listOf(
        GenreJson(1, "Horror"),
        GenreJson(2, "Action"),
        GenreJson(3, "Comedy")
    )
    val genreJson = GenreListJson(genreListJson)
}
