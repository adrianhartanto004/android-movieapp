package com.adrian.home.presentation.home.viewmodel

import android.app.Application
import com.adrian.abstraction.common.network.enum.ErrorStatus
import com.adrian.abstraction.common.state.UIState
import com.adrian.abstraction.common.state.UseCaseResult
import com.adrian.home.R
import com.adrian.home.domain.TestData
import com.adrian.home.domain.usecase.GetGenresUseCase
import com.adrian.home.domain.usecase.GetNowPlayingMoviesUseCase
import com.adrian.home.domain.usecase.GetPopularMoviesUseCase
import com.adrian.test_util.BaseTest
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert.assertEquals
import org.junit.Test
import java.io.IOException

@ExperimentalCoroutinesApi
internal class HomeViewModelTest : BaseTest() {

    // region constants--------------------------------------------------
    // endregion constants-----------------------------------------------

    // region helper fields----------------------------------------------
    // endregion helper fields-------------------------------------------

    @MockK(relaxed = true)
    internal lateinit var applicationMock: Application

    @MockK
    internal lateinit var getPopularMoviesUseCaseMock: GetPopularMoviesUseCase

    @MockK
    internal lateinit var getNowPlayingMoviesUseCaseMock: GetNowPlayingMoviesUseCase

    @MockK
    internal lateinit var getGenresUseCaseMock: GetGenresUseCase

    private lateinit var sut: HomeViewModel

    override fun setup() {
        MockKAnnotations.init(this)
        sut = HomeViewModel(
            applicationMock,
            getPopularMoviesUseCaseMock,
            getNowPlayingMoviesUseCaseMock,
            getGenresUseCaseMock
        )
    }

    @Test
    fun `execute all Home UseCases`() {
        //Arrange
        //Act
        sut.loadData()
        //Assert
        coVerify { getPopularMoviesUseCaseMock.getPopularMovies(1) }
        coVerify { getNowPlayingMoviesUseCaseMock.getNowPlayingMovies(1) }
        coVerify { getGenresUseCaseMock.getGenres() }
    }

    //Popular Movies
    @Test
    fun `fetch popularMovies popularMoviesListData returned`() {
        //given
        coEvery { getPopularMoviesUseCaseMock.getPopularMovies(1) } returns UseCaseResult.Success(
            TestData.popularMoviesList
        )
        //when
        sut.getPopularMovies(1)
        //then
        assertEquals(
            sut.popularMoviesLiveData.value,
            UIState.Success(TestData.popularMoviesListData)
        )
    }

    @Test
    fun `fetch popularMovies useCase exceptionError applicationErrorReturned`() {
        //given
        val exception = IOException()
        coEvery { getPopularMoviesUseCaseMock.getPopularMovies(1) } returns UseCaseResult.Error(
            exception
        )
        //when
        sut.getPopularMovies(1)
        //then
        assertEquals(
            sut.popularMoviesLiveData.value,
            UIState.Failure(
                ErrorStatus.APPLICATION_ERROR,
                applicationMock.getString(R.string.default_application_error)
            )
        )
    }

    //NowPlaying Movies
    @Test
    fun `fetch nowPlayingMovies nowPlayingMoviesListData returned`() {
        //given
        coEvery { getNowPlayingMoviesUseCaseMock.getNowPlayingMovies(1) } returns UseCaseResult.Success(
            TestData.nowPlayingMoviesList
        )
        //when
        sut.getNowPlayingMovies(1)
        //then
        assertEquals(
            sut.nowPlayingMoviesLiveData.value,
            UIState.Success(TestData.nowPlayingMoviesListData)
        )
    }

    @Test
    fun `fetch nowPlayingMovies useCase exceptionError applicationErrorReturned`() {
        //given
        val exception = IOException()
        coEvery { getNowPlayingMoviesUseCaseMock.getNowPlayingMovies(1) } returns UseCaseResult.Error(
            exception
        )
        //when
        sut.getNowPlayingMovies(1)
        //then
        assertEquals(
            sut.nowPlayingMoviesLiveData.value,
            UIState.Failure(
                ErrorStatus.APPLICATION_ERROR,
                applicationMock.getString(R.string.default_application_error)
            )
        )
    }

    //Genres
    @Test
    fun `fetch genres genresListData returned`() {
        //given
        coEvery { getGenresUseCaseMock.getGenres() } returns UseCaseResult.Success(
            TestData.genresListData
        )
        //when
        sut.getGenres()
        //then
        assertEquals(sut.genresLiveData.value, UIState.Success(TestData.genresListData))
    }

    @Test
    fun `fetch genres useCase exceptionError applicationErrorReturned`() {
        //given
        val exception = IOException()
        coEvery { getGenresUseCaseMock.getGenres() } returns UseCaseResult.Error(
            exception
        )
        //when
        sut.getGenres()
        //then
        assertEquals(
            sut.genresLiveData.value,
            UIState.Failure(
                ErrorStatus.APPLICATION_ERROR,
                applicationMock.getString(R.string.default_application_error)
            )
        )
    }

    //Genres
    // region helper methods---------------------------------------------
    // endregion helper methods------------------------------------------

    // region helper classes---------------------------------------------
    // endregion helper classes------------------------------------------
}