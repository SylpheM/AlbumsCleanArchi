package com.sylphem.albums.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sylphem.core.CoroutineContextProvider
import com.sylphem.core.domain.model.AlbumItem
import com.sylphem.core.domain.usecase.GetAlbumsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class AlbumsViewModel @Inject constructor(
    private val getAlbums: GetAlbumsUseCase,
    private val coroutineContextProvider : CoroutineContextProvider
) : ViewModel() {

    val uiState: StateFlow<AlbumsUiState> = flow<AlbumsUiState> {
        val albums = withContext(coroutineContextProvider.IO) { getAlbums() }
        emit(AlbumsUiState.Success(albums))
    }
        .catch { emit(AlbumsUiState.Error(it)) }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), AlbumsUiState.Loading)

    sealed class AlbumsUiState {
        data object Loading : AlbumsUiState()
        data class Error(val throwable: Throwable) : AlbumsUiState()
        data class Success(val data: List<AlbumItem>) : AlbumsUiState()
    }
}