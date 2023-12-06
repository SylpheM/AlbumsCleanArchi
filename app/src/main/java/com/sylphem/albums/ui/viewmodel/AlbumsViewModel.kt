package com.sylphem.albums.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sylphem.albums.domain.repository.AlbumsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class AlbumsViewModel @Inject constructor(
    private val repository: AlbumsRepository
) : ViewModel() {

    init{
        viewModelScope.launch {
            val albums = withContext(Dispatchers.IO) { repository.getAlbumsList() }
            Log.d("Albums", albums.toString())
        }
    }

}