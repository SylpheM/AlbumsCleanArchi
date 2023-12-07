package com.sylphem.albums.ui.compose

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sylphem.albums.domain.model.AlbumItem
import com.sylphem.albums.ui.theme.AlbumsTheme
import com.sylphem.albums.ui.viewmodel.AlbumsViewModel
import com.sylphem.albums.ui.viewmodel.AlbumsViewModel.AlbumsUiState

@Composable
fun AlbumsScreen(
    modifier: Modifier = Modifier, viewModel: AlbumsViewModel = hiltViewModel()
) {
    val uiState: AlbumsUiState = viewModel.uiState.collectAsStateWithLifecycle().value
    Surface(
        modifier = modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
    ) {
        AlbumsScreen(uiState)
    }
}

@Composable
fun AlbumsScreen(uiState: AlbumsUiState, modifier: Modifier = Modifier) {
    when (uiState) {
        AlbumsUiState.Loading -> LoadingCircle()

        is AlbumsUiState.Success -> AlbumsList(uiState.data)

        is AlbumsUiState.Error -> ErrorText(uiState.throwable.localizedMessage)
    }
}

@Composable
fun LoadingCircle() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(46.dp)
    ) {
        CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
    }
}

@Composable
fun AlbumsList(albums: List<AlbumItem>) {
    LazyColumn {
        itemsIndexed(albums) { index, album ->
            Box(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    modifier = Modifier.padding(8.dp),
                    style = MaterialTheme.typography.labelMedium,
                    text = album.title
                )
            }
            if (index < albums.size - 1) {
                Divider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp)
                )
            }
        }
    }
}

@Composable
fun ErrorText(localizedMessage: String?) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(46.dp)
    ) {
        Text(
            style = MaterialTheme.typography.labelLarge,
            text = localizedMessage ?: ""
        )
    }
}

@Preview(showBackground = true)
@Composable
fun AlbumsScreenLoadingPreview() {
    AlbumsTheme {
        AlbumsScreen(uiState = AlbumsUiState.Loading)
    }
}

@Preview(showBackground = true)
@Composable
fun AlbumsScreenWithDataPreview() {
    AlbumsTheme {
        AlbumsScreen(
            uiState = AlbumsUiState.Success(
                listOf(
                    AlbumItem(
                        id = 1,
                        albumId = 1,
                        title = "Album title 1",
                        url = "",
                        thumbnailUrl = ""
                    ),
                    AlbumItem(
                        id = 1,
                        albumId = 1,
                        title = "Album title 2",
                        url = "",
                        thumbnailUrl = ""
                    )
                )
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun AlbumsScreenErrorPreview() {
    AlbumsTheme {
        AlbumsScreen(uiState = AlbumsUiState.Error(Exception("Error")))
    }
}