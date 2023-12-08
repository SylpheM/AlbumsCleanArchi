package com.sylphem.albums.data

import com.sylphem.albums.data.model.RemoteAlbumItem
import com.sylphem.albums.data.remote.AlbumsApiService
import com.sylphem.core.domain.model.AlbumItem
import com.sylphem.core.domain.repository.AlbumsRepository
import javax.inject.Inject

class AlbumsRepositoryImpl @Inject constructor(
    private val albumsApiService: AlbumsApiService
) : AlbumsRepository {
    override suspend fun getAlbumsList(): List<AlbumItem> =
        albumsApiService.getAlbumsList().map {
            it.convert()
        }
}

private fun RemoteAlbumItem.convert() = AlbumItem(
    id = id ?: -1,
    albumId = albumId ?: -1,
    title = title ?: "",
    url = url ?: "",
    thumbnailUrl = thumbnailUrl ?: ""
)