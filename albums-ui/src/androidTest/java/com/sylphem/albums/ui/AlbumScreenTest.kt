package com.sylphem.albums.ui

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.sylphem.albums.ui.compose.AlbumsScreen
import com.sylphem.albums.ui.viewmodel.AlbumsViewModel
import com.sylphem.core.domain.model.AlbumItem
import com.sylphem.core.ui.theme.AlbumsTheme
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class AlbumScreenTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun loadingDisplay() {
        composeTestRule.setContent {
            AlbumsTheme {
                AlbumsScreen(uiState = AlbumsViewModel.AlbumsUiState.Loading)
            }
        }

        composeTestRule.onNodeWithText("Loading…").assertIsDisplayed()
    }

    @Test
    fun successDisplay() {
        composeTestRule.setContent {
            AlbumsTheme {
                AlbumsScreen(
                    uiState = AlbumsViewModel.AlbumsUiState.Success(
                        listOf(
                            fakeAlbumItem(1),
                            fakeAlbumItem(2)
                        )
                    )
                )
            }
        }

        composeTestRule.onNodeWithText("Album title 1").assertIsDisplayed()
        composeTestRule.onNodeWithText("Album title 2").assertIsDisplayed()
    }

    @Test
    fun errorDisplay() {
        composeTestRule.setContent {
            AlbumsTheme {
                AlbumsScreen(
                    uiState = AlbumsViewModel.AlbumsUiState.Error(
                        IOException("Network error")
                    )
                )
            }
        }

        composeTestRule.onNodeWithText("Network error", true).assertIsDisplayed()
    }
}

fun fakeAlbumItem(id: Long) = AlbumItem(
    id = id,
    albumId = 1,
    title = "Album title $id",
    url = "",
    thumbnailUrl = "https://via.placeholder.com/150/771796"
)