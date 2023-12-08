package com.sylphem.core.domain.repository

import com.sylphem.core.domain.model.AlbumItem

interface NetworkAlbumsDataSource {
    suspend fun getAlbumsList(): List<AlbumItem>
}