package com.sylphem.core.domain.repository

import com.sylphem.core.domain.model.AlbumItem

interface DatabaseAlbumsDataSource {
    suspend fun getAlbumsList(): List<AlbumItem>

    suspend fun saveAlbumsList(albums: List<AlbumItem>)
}