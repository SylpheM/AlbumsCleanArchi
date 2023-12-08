package com.sylphem.albums.data.database.di

import com.sylphem.albums.data.database.DatabaseAlbumsDataSourceImpl
import com.sylphem.core.domain.repository.DatabaseAlbumsDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AlbumsDatabaseModule {

    @Singleton
    @Provides
    fun providesDatabaseAlbumsDataSource(): DatabaseAlbumsDataSource =
        DatabaseAlbumsDataSourceImpl()
}