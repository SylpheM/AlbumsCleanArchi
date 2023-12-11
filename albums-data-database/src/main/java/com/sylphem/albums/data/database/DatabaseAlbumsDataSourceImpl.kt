package com.sylphem.albums.data.database

import com.sylphem.albums.data.database.model.LocalAlbumItem
import com.sylphem.albums.data.database.model.LocalAlbumItemDao
import com.sylphem.core.domain.model.AlbumItem
import com.sylphem.core.domain.repository.DatabaseAlbumsDataSource
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class DatabaseAlbumsDataSourceImpl @Inject constructor(
    private val albumsItemDao: LocalAlbumItemDao
) : DatabaseAlbumsDataSource {

    override suspend fun getAlbumsList(): List<AlbumItem> {
        return albumsItemDao.getAlbums().first().map { it.convert() }
    }

    override suspend fun saveAlbumsList(albums: List<AlbumItem>) {
        albumsItemDao.insertAlbums(albums.map { it.convert() })
    }

    private fun LocalAlbumItem.convert() = AlbumItem(
        id = this.id,
        albumId = this.albumId,
        title = this.title,
        url = this.url,
        thumbnailUrl = this.thumbnailUrl
    )

    private fun AlbumItem.convert() = LocalAlbumItem(
        id = this.id,
        albumId = this.albumId,
        title = this.title,
        url = this.url,
        thumbnailUrl = this.thumbnailUrl
    )
}