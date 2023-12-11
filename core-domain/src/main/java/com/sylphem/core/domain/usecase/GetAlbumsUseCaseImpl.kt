package com.sylphem.core.domain.usecase

import com.sylphem.core.domain.model.AlbumItem
import com.sylphem.core.domain.repository.DatabaseAlbumsDataSource
import com.sylphem.core.domain.repository.NetworkAlbumsDataSource

class GetAlbumsUseCaseImpl(
    private val networkAlbumsDataSource: NetworkAlbumsDataSource,
    private val databaseAlbumsDataSource: DatabaseAlbumsDataSource
) : GetAlbumsUseCase {
    override suspend operator fun invoke(): List<AlbumItem> =
        try {
            networkAlbumsDataSource.getAlbumsList().apply {
                databaseAlbumsDataSource.saveAlbumsList(this)
            }
        } catch (e: Exception) {
            databaseAlbumsDataSource.getAlbumsList()
        }
}