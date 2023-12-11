package com.sylphem.albums.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sylphem.albums.data.database.model.LocalAlbumItem
import com.sylphem.albums.data.database.model.LocalAlbumItemDao

@Database(entities = [LocalAlbumItem::class], version = 1)
abstract class AlbumsDatabase : RoomDatabase() {
    abstract fun localAlbumItemDao(): LocalAlbumItemDao
}