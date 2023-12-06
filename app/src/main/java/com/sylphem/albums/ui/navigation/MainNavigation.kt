package com.sylphem.albums.ui.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.sylphem.albums.ui.compose.AlbumsScreen
import com.sylphem.albums.ui.navigation.Destination.AlbumsList

@Composable
fun MainNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = AlbumsList.route) {
        composable(AlbumsList.route) { AlbumsScreen(modifier = Modifier.padding(16.dp)) }
    }
}