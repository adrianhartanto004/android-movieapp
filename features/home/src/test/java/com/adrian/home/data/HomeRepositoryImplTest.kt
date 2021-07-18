package com.adrian.home.data

import com.adrian.abstraction.common.data.SharedDao
import com.adrian.home.data.database.HomeDao
import com.adrian.home.data.network.model.genre.toDomainModel
import com.adrian.home.data.network.model.genre.toEntity
import com.adrian.home.data.network.model.nowplayingmovies.toDomainModel
import com.adrian.home.data.network.model.nowplayingmovies.toEntity
import com.adrian.home.data.network.model.popularmovies.toDomainModel
import com.adrian.home.data.network.model.popularmovies.toEntity
import com.adrian.home.data.network.service.HomeRetrofitService
import com.adrian.home.domain.TestData
import com.adrian.home.domain.model.genre.Genre
import com.adrian.home.domain.model.nowplayingmovies.NowPlayingMoviesList
import com.adrian.home.domain.model.popularmovies.PopularMoviesList
import com.adrian.test_util.BaseTest
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test
import java.io.IOException

@ExperimentalCoroutinesApi
internal class HomeRepositoryImplTest : BaseTest() {

    // region constants--------------------------------------------------
    // endregion constants-----------------------------------------------

    // region helper fields----------------------------------------------
    // endregion helper fields-------------------------------------------
    @MockK
    internal lateinit var serviceMock: HomeRetrofitService

    @MockK
    internal lateinit var homeDaoMock: HomeDao

    @MockK
    internal lateinit var sharedDaoMock: SharedDao

    private lateinit var sut: HomeRepositoryImpl

    override fun setup() {
        MockKAnnotations.init(this)
        sut = HomeRepositoryImpl(serviceMock, homeDaoMock, sharedDaoMock)
    }

    @Test
    fun `fetches PopularMoviesJson and maps to PopularMoviesList`() {
        //given
        var resultList = PopularMoviesList(0, emptyList(), 0, 0)
        coEvery { serviceMock.getPopularMovies(1) } returns TestData.popularMovieListJson
        coEvery {
            homeDaoMock.addPopularMovies(any())
        } returns Unit
        //when
        val result = runBlocking { sut.getPopularMovies(1) }
        runBlocking {
            result.collect {
                resultList = it
            }
        }
        //then
        assertEquals(resultList, TestData.popularMovieListJson.toDomainModel())
    }

    @Test
    fun `fetches PopularMovies return data from database if offline`() {
        // given
        var resultList = PopularMoviesList(0, emptyList(), 0, 0)
        coEvery { serviceMock.getPopularMovies(1) } throws IOException()
        coEvery { homeDaoMock.getAllPopularMovies() } returns TestData.popularMovieListJson.results.map { it.toEntity() }
        // when
        val result = runBlocking { sut.getPopularMovies(1) }
        runBlocking {
            result.collect {
                resultList = it
            }
        }
        // then
        assertEquals(resultList, TestData.popularMovieListJson.toDomainModel())
    }

    @Test
    fun `fetches NowPlayingMoviesJson and maps to NowPlayingMoviesList`() {
        //given
        var resultList = NowPlayingMoviesList(0, emptyList(), 0, 0)
        coEvery { serviceMock.getNowPlayingMovies(1) } returns TestData.nowPlayingMoviesListJson
        coEvery {
            homeDaoMock.addNowPlayingMovies(any())
        } returns Unit
        //when
        val result = runBlocking { sut.getNowPlayingMovies(1) }
        runBlocking {
            result.collect {
                resultList = it
            }
        }
        //then
        assertEquals(resultList, TestData.nowPlayingMoviesListJson.toDomainModel())
    }

    @Test
    fun `fetches NowPlayingMovies return data from database if offline`() {
        // given
        var resultList = NowPlayingMoviesList(0, emptyList(), 0, 0)
        coEvery { serviceMock.getNowPlayingMovies(1) } throws IOException()
        coEvery { homeDaoMock.getAllNowPlayingMovies() } returns TestData.nowPlayingMoviesListJson.results.map { it.toEntity() }
        // when
        val result = runBlocking { sut.getNowPlayingMovies(1) }
        runBlocking {
            result.collect {
                resultList = it
            }
        }
        // then
        assertEquals(resultList, TestData.nowPlayingMoviesListJson.toDomainModel())
    }

    @Test
    fun `fetches Genres from database and return data if not empty`() {
        //given
        var resultList = listOf<Genre>()
        coEvery { homeDaoMock.getAllGenres() } returns TestData.genreListJson.map { it.toEntity() }
        //when
        val result = runBlocking { sut.getGenres() }
        runBlocking {
            result.collect {
                resultList = it
            }
        }
        //then
        assertEquals(resultList, TestData.genreListJson.map { it.toDomainModel() })
    }

    @Test
    fun `fetches GenresJson and maps to Genre`() {
        //given
        var resultList = listOf<Genre>()
        coEvery { homeDaoMock.getAllGenres() } returns listOf()
        coEvery { serviceMock.getGenres() } returns TestData.genreJson
        coEvery {
            homeDaoMock.addGenres(any())
        } returns Unit
        //when
        val result = runBlocking { sut.getGenres() }
        runBlocking {
            result.collect {
                resultList = it
            }
        }
        //then
        assertEquals(resultList, TestData.genreListJson.map { it.toDomainModel() })
    }

    @Test
    fun `fetches GenresJson return data from database if offline`() {
        // given
        var resultList = listOf<Genre>()
        coEvery { homeDaoMock.getAllGenres() } returns listOf()
        coEvery { serviceMock.getGenres() } throws IOException()
        coEvery { homeDaoMock.getAllGenres() } returns TestData.genreListJson.map { it.toEntity() }
        // when
        val result = runBlocking { sut.getGenres() }
        runBlocking {
            result.collect {
                resultList = it
            }
        }
        // then
        assertEquals(resultList, TestData.genreListJson.map { it.toDomainModel() })
    }

    // region helper methods---------------------------------------------
    // endregion helper methods------------------------------------------

    // region helper classes---------------------------------------------
    // endregion helper classes------------------------------------------
}
