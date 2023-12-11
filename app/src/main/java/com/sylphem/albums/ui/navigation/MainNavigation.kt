package com.sylphem.albums.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.sylphem.albums.ui.compose.AlbumsScreen
import com.sylphem.albums.ui.navigation.Destination.AlbumsList

@Composable
fun MainNavigation(navHostController: NavHostController = rememberNavController()) {

    NavHost(navController = navHostController, startDestination = AlbumsList.route) {
        composable(AlbumsList.route) { AlbumsScreen() }
    }
}