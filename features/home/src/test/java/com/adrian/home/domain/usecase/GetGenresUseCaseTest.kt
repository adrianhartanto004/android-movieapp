package com.adrian.home.domain.usecase

import com.adrian.abstraction.common.state.UseCaseResult
import com.adrian.home.domain.TestData
import com.adrian.home.domain.repository.HomeRepository
import com.adrian.test_util.BaseTest
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test
import java.io.IOException

@ExperimentalCoroutinesApi
internal class GetGenresUseCaseTest : BaseTest() {

    // region constants--------------------------------------------------
    // endregion constants-----------------------------------------------

    // region helper fields----------------------------------------------
    // endregion helper fields-------------------------------------------

    @MockK
    internal lateinit var repositoryMock: HomeRepository

    private lateinit var sut: GetGenresUseCase

    override fun setup() {
        MockKAnnotations.init(this)
        sut = GetGenresUseCase(repositoryMock)
    }

    @Test
    fun `success nowPlayingMoviesListData returned`() {
        // given
        coEvery { repositoryMock.getGenres() } returns TestData.genresListData
        // when
        val result = runBlocking { sut.getGenres() }
        // then
        assertEquals(result, TestData.genresListData)
    }

    // region helper methods---------------------------------------------
    // endregion helper methods------------------------------------------

    // region helper classes---------------------------------------------
    // endregion helper classes------------------------------------------
}
