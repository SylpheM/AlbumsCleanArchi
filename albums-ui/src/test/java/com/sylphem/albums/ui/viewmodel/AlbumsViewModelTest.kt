package com.sylphem.albums.ui.viewmodel

import com.sylphem.albums.core.CoroutinesTestRule
import com.sylphem.albums.ui.viewmodel.AlbumsViewModel.AlbumsUiState.Error
import com.sylphem.core.domain.model.AlbumItem
import com.sylphem.core.domain.usecase.GetAlbumsUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import java.io.IOException

@OptIn(ExperimentalCoroutinesApi::class)
class AlbumsViewModelTest {

    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()

    private lateinit var viewModel: AlbumsViewModel

    @Test
    fun uiStateLoading() = runTest {
        //Given
        val useCase = FakeGetAlbumsUseCase()
        viewModel = AlbumsViewModel(useCase, coroutinesTestRule.testCoroutineContextProvider)

        //Then
        assertEquals(AlbumsViewModel.AlbumsUiState.Loading, viewModel.uiState.value)
    }

    @Test
    fun uiStateSuccess() = runTest {
        //Given
        //Given
        val useCase = FakeGetAlbumsUseCase()
        viewModel = AlbumsViewModel(useCase, coroutinesTestRule.testCoroutineContextProvider)
        val albums = listOf(
            fakeAlbumItem(1),
            fakeAlbumItem(2)
        )
        useCase.albums.addAll(albums)

        //When
        val resultUiStateList = mutableListOf<AlbumsViewModel.AlbumsUiState>()
        backgroundScope.launch(UnconfinedTestDispatcher(testScheduler)) {
            viewModel.uiState.toList(resultUiStateList)
        }

        //Then
        assertEquals(2, resultUiStateList.size)
        assertEquals(AlbumsViewModel.AlbumsUiState.Loading, resultUiStateList[0])
        assertEquals(AlbumsViewModel.AlbumsUiState.Success(albums), resultUiStateList[1])
    }

    @Test
    fun uiStateError() = runTest {
        //Given
        val useCase = FakeErrorGetAlbumsUseCase()
        viewModel = AlbumsViewModel(useCase, coroutinesTestRule.testCoroutineContextProvider)

        //When
        val resultUiStateList = mutableListOf<AlbumsViewModel.AlbumsUiState>()
        backgroundScope.launch(UnconfinedTestDispatcher(testScheduler)) {
            viewModel.uiState.toList(resultUiStateList)
        }

        //Then
        assertEquals(2, resultUiStateList.size)
        assertEquals(AlbumsViewModel.AlbumsUiState.Loading, resultUiStateList[0])
        val secondUiState = resultUiStateList[1]
        assertTrue(secondUiState is Error)
        assertTrue((secondUiState as Error).throwable is IOException)
    }

}

private class FakeGetAlbumsUseCase : GetAlbumsUseCase {
    val albums = mutableListOf<AlbumItem>()
    override suspend fun invoke(): List<AlbumItem> = albums
}

private class FakeErrorGetAlbumsUseCase : GetAlbumsUseCase {
    override suspend fun invoke(): List<AlbumItem> {
        throw IOException()
    }
}

fun fakeAlbumItem(id: Long) = AlbumItem(
    id = id,
    albumId = 1,
    title = "Album title $id",
    url = "",
    thumbnailUrl = "https://via.placeholder.com/150/771796"
)