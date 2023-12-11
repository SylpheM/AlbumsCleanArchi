package com.sylphem.albums.data.database.di

import android.content.Context
import androidx.room.Room
import com.sylphem.albums.data.database.AlbumsDatabase
import com.sylphem.albums.data.database.DatabaseAlbumsDataSourceImpl
import com.sylphem.albums.data.database.model.LocalAlbumItemDao
import com.sylphem.core.domain.repository.DatabaseAlbumsDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AlbumsDatabaseModule {

    @Provides
    fun providesLocalAlbumItemDao(database: AlbumsDatabase): LocalAlbumItemDao =
        database.localAlbumItemDao()

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): AlbumsDatabase =
        Room.databaseBuilder(
            appContext,
            AlbumsDatabase::class.java,
            "LocalAlbums"
        ).build()

    @Singleton
    @Provides
    fun providesDatabaseAlbumsDataSource(localAlbumItemDao: LocalAlbumItemDao): DatabaseAlbumsDataSource =
        DatabaseAlbumsDataSourceImpl(localAlbumItemDao)
}