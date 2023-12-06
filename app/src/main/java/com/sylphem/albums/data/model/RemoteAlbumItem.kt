package com.sylphem.albums.data.model

import kotlinx.serialization.Serializable

@Serializable
data class RemoteAlbumItem (
    val id: Long?,
    val albumId: Long?,
    val title: String?,
    val url: String?,
    val thumbnailUrl: String?
)