package com.sylphem.albums.core.domain.usecase

import com.sylphem.core.domain.model.AlbumItem
import com.sylphem.core.domain.repository.DatabaseAlbumsDataSource
import com.sylphem.core.domain.repository.NetworkAlbumsDataSource
import com.sylphem.core.domain.usecase.GetAlbumsUseCase
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test
import java.io.IOException

class GetAlbumsUseCaseTest {

    @Test
    fun invokeNetworkSuccess() = runTest {
        //Given
        val networkDataSource = FakeNetworkAlbumsDataSource()
        val databaseDataSource = FakeDatabaseAlbumsDataSource()
        val useCase = GetAlbumsUseCase(networkDataSource, databaseDataSource)
        val albums = listOf(
            fakeAlbumItem(1),
            fakeAlbumItem(2)
        )
        networkDataSource.albumsList.addAll(albums)

        //When
        val result = useCase.invoke()

        //Then
        assertEquals(result, albums)
        assertEquals(result, databaseDataSource.getAlbumsList())
    }

    @Test
    fun invokeDatabaseSuccess() = runTest {
        //Given
        val networkDataSource = FakeErrorNetworkAlbumsDataSource()
        val databaseDataSource = FakeDatabaseAlbumsDataSource()
        val useCase = GetAlbumsUseCase(networkDataSource, databaseDataSource)
        val albums = listOf(
            fakeAlbumItem(1),
            fakeAlbumItem(2)
        )
        databaseDataSource.albumsList.addAll(albums)

        //When
        val result = useCase.invoke()

        //Then
        assertEquals(result, albums)
    }
}

private class FakeDatabaseAlbumsDataSource : DatabaseAlbumsDataSource {
    val albumsList = mutableListOf<AlbumItem>()

    override suspend fun getAlbumsList(): List<AlbumItem> = albumsList

    override suspend fun saveAlbumsList(albums: List<AlbumItem>) {
        albumsList.addAll(albums)
    }

}

private class FakeNetworkAlbumsDataSource : NetworkAlbumsDataSource {
    val albumsList = mutableListOf<AlbumItem>()

    override suspend fun getAlbumsList(): List<AlbumItem> = albumsList
}


private class FakeErrorNetworkAlbumsDataSource : NetworkAlbumsDataSource {
    override suspend fun getAlbumsList(): List<AlbumItem> {
        throw IOException()
    }
}

fun fakeAlbumItem(id: Long) = AlbumItem(
    id = id,
    albumId = 1,
    title = "Album title $id",
    url = "",
    thumbnailUrl = "https://via.placeholder.com/150/771796"
)