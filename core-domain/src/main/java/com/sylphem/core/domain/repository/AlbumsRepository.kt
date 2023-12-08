package com.sylphem.core.domain.repository

import com.sylphem.core.domain.model.AlbumItem

interface AlbumsRepository {
    suspend fun getAlbumsList(): List<AlbumItem>
}