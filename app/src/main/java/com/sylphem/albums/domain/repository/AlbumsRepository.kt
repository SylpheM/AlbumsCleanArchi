package com.sylphem.albums.domain.repository

import com.sylphem.albums.domain.model.AlbumItem
import kotlinx.coroutines.flow.Flow

interface AlbumsRepository {
    fun getAlbumsList(): Flow<List<AlbumItem>>
}