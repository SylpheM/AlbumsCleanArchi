package com.sylphem.albums.data.network

import com.sylphem.albums.data.network.model.RemoteAlbumItem
import com.sylphem.albums.data.network.remote.AlbumsApiService
import com.sylphem.core.domain.model.AlbumItem
import com.sylphem.core.domain.repository.NetworkAlbumsDataSource
import javax.inject.Inject

class NetworkAlbumsDataSourceImpl @Inject constructor(
    private val albumsApiService: AlbumsApiService
) : NetworkAlbumsDataSource {
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