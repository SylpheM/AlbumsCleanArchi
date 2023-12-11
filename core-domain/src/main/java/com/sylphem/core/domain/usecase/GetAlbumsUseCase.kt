package com.sylphem.core.domain.usecase

import com.sylphem.core.domain.model.AlbumItem
import com.sylphem.core.domain.repository.DatabaseAlbumsDataSource
import com.sylphem.core.domain.repository.NetworkAlbumsDataSource

class GetAlbumsUseCase(
    private val networkAlbumsDataSource: NetworkAlbumsDataSource,
    private val databaseAlbumsDataSource: DatabaseAlbumsDataSource
) {
    suspend operator fun invoke(): List<AlbumItem> =
        databaseAlbumsDataSource.getAlbumsList().let { albums ->
            albums.ifEmpty {
                networkAlbumsDataSource.getAlbumsList().apply {
                    databaseAlbumsDataSource.saveAlbumsList(this)
                }
            }
        }
}