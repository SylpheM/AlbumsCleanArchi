package com.sylphem.albums.data.database

import com.sylphem.albums.data.database.model.LocalAlbumItem
import com.sylphem.albums.data.database.model.LocalAlbumItemDao
import com.sylphem.core.domain.model.AlbumItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class DatabaseAlbumsDataSourceImplTest {

    @Test
    fun getAlbumsListReturnsEmpty() = runTest {
        //Given
        val dataSource = DatabaseAlbumsDataSourceImpl(FakeLocalAlbumItemDao())

        //When
        val resultAlbums = dataSource.getAlbumsList()

        //Then
        assertTrue(resultAlbums.isEmpty())
    }

    @Test
    fun saveAlbumsListSuccess() = runTest {
        //Given
        val dataSource = DatabaseAlbumsDataSourceImpl(FakeLocalAlbumItemDao())

        //When
        dataSource.saveAlbumsList(
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

        //Then
        assertEquals(dataSource.getAlbumsList().size, 2)
    }

    @Test
    fun getAlbumsListSuccess() = runTest {
        //Given
        val dataSource = DatabaseAlbumsDataSourceImpl(FakeLocalAlbumItemDao())
        val albums = listOf(
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
        dataSource.saveAlbumsList(albums)

        //When
        val resultAlbums = dataSource.getAlbumsList()

        //Then
        assertEquals(resultAlbums, albums)
    }
}

private class FakeLocalAlbumItemDao : LocalAlbumItemDao {

    private val data = mutableListOf<LocalAlbumItem>()

    override fun getAlbums(): Flow<List<LocalAlbumItem>> = flow {
        emit(data)
    }

    override suspend fun insertAlbums(albums: List<LocalAlbumItem>) {
        data.addAll(albums)
    }
}