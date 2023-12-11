package com.sylphem.core.domain.usecase

import com.sylphem.core.domain.model.AlbumItem

interface GetAlbumsUseCase {
    suspend operator fun invoke(): List<AlbumItem>
}