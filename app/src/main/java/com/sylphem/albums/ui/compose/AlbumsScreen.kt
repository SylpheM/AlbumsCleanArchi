package com.sylphem.albums.ui.compose

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.sylphem.albums.R
import com.sylphem.core.ui.theme.AlbumsTheme
import com.sylphem.albums.ui.viewmodel.AlbumsViewModel
import com.sylphem.albums.ui.viewmodel.AlbumsViewModel.AlbumsUiState
import com.sylphem.core.domain.model.AlbumItem

@Composable
fun AlbumsScreen(
    modifier: Modifier = Modifier, viewModel: AlbumsViewModel = hiltViewModel()
) {
    val uiState: AlbumsUiState = viewModel.uiState.collectAsStateWithLifecycle().value
    Surface(
        modifier = modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        AlbumsScreen(uiState)
    }
}

@Composable
fun AlbumsScreen(uiState: AlbumsUiState) {
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
            .padding(32.dp)
    ) {
        CircularProgressIndicator(
            modifier = Modifier.align(Alignment.Center),
            color = MaterialTheme.colorScheme.primary
        )
    }
}

@Composable
fun AlbumCell(album: AlbumItem) {
    Row(modifier = Modifier.padding(8.dp)) {
        AsyncImage(
            model = album.thumbnailUrl,
            contentDescription = stringResource(R.string.thumbnail),
            modifier = Modifier.size(56.dp),
            placeholder = ColorPainter(MaterialTheme.colorScheme.secondaryContainer),
            error = ColorPainter(MaterialTheme.colorScheme.secondaryContainer)
        )
        Text(
            text = album.title,
            modifier = Modifier.padding(8.dp),
            style = MaterialTheme.typography.labelMedium
        )
    }
}

@Composable
fun AlbumsList(albums: List<AlbumItem>) {
    val localContext = LocalContext.current
    LazyColumn {
        itemsIndexed(albums) { index, album ->
            val detail = stringResource(id = R.string.album_detail, album.id, album.albumId)
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        Toast
                            .makeText(localContext, detail, Toast.LENGTH_SHORT)
                            .show()
                    }
            ) {
                AlbumCell(album)
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
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(32.dp)
    ) {
        Image(
            painter = painterResource(id = android.R.drawable.stat_notify_error),
            contentDescription = null,
            colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.error)
        )

        Text(
            text = stringResource(id = R.string.error_message),
            style = MaterialTheme.typography.labelLarge
        )

        Text(
            text = stringResource(id = R.string.error_detail, localizedMessage ?: ""),
            modifier = Modifier.padding(vertical = 8.dp),
            style = MaterialTheme.typography.labelSmall
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
                        thumbnailUrl = "https://via.placeholder.com/150/92c952"
                    ),
                    AlbumItem(
                        id = 1,
                        albumId = 1,
                        title = "Album title 2",
                        url = "",
                        thumbnailUrl = "https://via.placeholder.com/150/771796"
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
        AlbumsScreen(uiState = AlbumsUiState.Error(Exception("No internet")))
    }
}