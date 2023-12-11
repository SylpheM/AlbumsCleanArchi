package com.sylphem.albums.data.network

import com.sylphem.albums.data.network.model.RemoteAlbumItem
import com.sylphem.albums.data.network.remote.AlbumsApiService
import com.sylphem.core.domain.model.AlbumItem
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import java.io.IOException

class NetworkAlbumsDataSourceImplTest {

    @Test
    fun getAlbumsListSuccess() = runTest {
        //Given
        val dataSource = NetworkAlbumsDataSourceImpl(FakeAlbumsApiService())

        //When
        val result = dataSource.getAlbumsList()

        //Then
        assertEquals(
            result, listOf(
                AlbumItem(
                    id = 1,
                    albumId = 1,
                    title = "Album title 1",
                    url = "",
                    thumbnailUrl = "https://via.placeholder.com/150/92c952"
                ),
                AlbumItem(
                    id = -1,
                    albumId = -1,
                    title = "",
                    url = "",
                    thumbnailUrl = ""
                )
            )
        )
    }

    @Test
    fun getAlbumsListError() = runTest {
        //Given
        val dataSource = NetworkAlbumsDataSourceImpl(FakeErrorAlbumsApiService())

        //When/Then
        runCatching { dataSource.getAlbumsList() }.onFailure { assertTrue(it is IOException) }
    }

    private class FakeAlbumsApiService : AlbumsApiService {
        override suspend fun getAlbumsList(): List<RemoteAlbumItem> {
            return listOf(
                RemoteAlbumItem(
                    id = 1,
                    albumId = 1,
                    title = "Album title 1",
                    url = "",
                    thumbnailUrl = "https://via.placeholder.com/150/92c952"
                ),
                RemoteAlbumItem(
                    id = null,
                    albumId = null,
                    title = null,
                    url = null,
                    thumbnailUrl = null
                )
            )
        }
    }
}

private class FakeErrorAlbumsApiService : AlbumsApiService {
    override suspend fun getAlbumsList(): List<RemoteAlbumItem> {
        throw IOException()
    }
}