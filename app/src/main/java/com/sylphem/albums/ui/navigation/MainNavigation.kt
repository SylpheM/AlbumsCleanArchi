package com.sylphem.albums.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.sylphem.albums.ui.compose.AlbumsScreen
import com.sylphem.albums.ui.navigation.Destination.AlbumsList

@Composable
fun MainNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = AlbumsList.route) {
        composable(AlbumsList.route) { AlbumsScreen() }
    }
}