package com.sylphem.albums.data.database.model

import androidx.room.Dao
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.PrimaryKey
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Entity
data class LocalAlbumItem(
    @PrimaryKey
    val id: Long,
    val albumId: Long,
    val title: String,
    val url: String,
    val thumbnailUrl: String
)

@Dao
interface LocalAlbumItemDao {
    @Query("SELECT * FROM localalbumitem ORDER BY id")
    fun getAlbums(): Flow<List<LocalAlbumItem>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAlbums(albums: List<LocalAlbumItem>)
}