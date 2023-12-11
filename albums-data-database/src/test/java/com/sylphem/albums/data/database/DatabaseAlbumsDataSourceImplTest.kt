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
                fakeAlbumItem(1),
                fakeAlbumItem(2)
            )
        )

        //Then
        assertEquals(2, dataSource.getAlbumsList().size)
    }

    @Test
    fun getAlbumsListSuccess() = runTest {
        //Given
        val dataSource = DatabaseAlbumsDataSourceImpl(FakeLocalAlbumItemDao())
        val albums = listOf(
            fakeAlbumItem(1),
            fakeAlbumItem(2)
        )
        dataSource.saveAlbumsList(albums)

        //When
        val resultAlbums = dataSource.getAlbumsList()

        //Then
        assertEquals(albums, resultAlbums)
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

fun fakeAlbumItem(id: Long) = AlbumItem(
    id = id,
    albumId = 1,
    title = "Album title $id",
    url = "",
    thumbnailUrl = "https://via.placeholder.com/150/771796"
)