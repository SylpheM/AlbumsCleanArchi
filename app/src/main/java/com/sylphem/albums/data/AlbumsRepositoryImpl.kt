package com.sylphem.albums.data

import com.sylphem.albums.data.model.RemoteAlbumItem
import com.sylphem.albums.data.remote.AlbumsApiService
import com.sylphem.albums.domain.model.AlbumItem
import com.sylphem.albums.domain.repository.AlbumsRepository
import javax.inject.Inject

class AlbumsRepositoryImpl @Inject constructor(
    private val albumsApiService: AlbumsApiService
) : AlbumsRepository {
    override suspend fun getAlbumsList(): List<AlbumItem> =
        try {
            albumsApiService.getAlbumsList().map {
                it.convert()
            }
        } catch (exception: Exception) {
            exception.printStackTrace()
            emptyList()
        }
}

private fun RemoteAlbumItem.convert() = AlbumItem(
    id = id ?: -1,
    albumId = albumId ?: -1,
    title = title ?: "",
    url = url ?: "",
    thumbnailUrl = thumbnailUrl ?: ""
)