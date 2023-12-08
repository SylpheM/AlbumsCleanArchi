package com.sylphem.core.domain.model

data class AlbumItem(
    val id: Long,
    val albumId: Long,
    val title: String,
    val url: String,
    val thumbnailUrl: String
)
