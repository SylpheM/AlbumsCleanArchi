package com.sylphem.albums.domain.repository

import com.sylphem.albums.domain.model.AlbumItem

interface AlbumsRepository {
    suspend fun getAlbumsList(): List<AlbumItem>
}