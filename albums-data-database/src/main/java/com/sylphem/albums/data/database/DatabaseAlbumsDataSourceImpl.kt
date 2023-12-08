package com.sylphem.albums.data.database

import com.sylphem.core.domain.model.AlbumItem
import com.sylphem.core.domain.repository.DatabaseAlbumsDataSource

class DatabaseAlbumsDataSourceImpl : DatabaseAlbumsDataSource {
    override suspend fun getAlbumsList(): List<AlbumItem> {
        return listOf(
            AlbumItem(
                id = 1,
                albumId = 1,
                title = "Album title 1",
                url = "",
                thumbnailUrl = "https://via.placeholder.com/150/92c952"
            ),
            AlbumItem(
                id = 1,
                albumId = 1,
                title = "Album title 2",
                url = "",
                thumbnailUrl = "https://via.placeholder.com/150/771796"
            )
        )
    }

    override fun saveAlbumsList(albums: List<AlbumItem>) {
        //TODO
    }
}