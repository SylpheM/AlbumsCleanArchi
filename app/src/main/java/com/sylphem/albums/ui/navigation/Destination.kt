package com.sylphem.albums.ui.navigation

sealed class Destination(val route: String) {
    data object AlbumsList : Destination("albumsList")
}