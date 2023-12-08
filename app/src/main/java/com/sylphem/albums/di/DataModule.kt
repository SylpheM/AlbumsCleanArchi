package com.sylphem.albums.di

import com.sylphem.albums.data.AlbumsRepositoryImpl
import com.sylphem.albums.data.remote.AlbumsApiService
import com.sylphem.core.domain.repository.AlbumsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {
    @Provides
    @Singleton
    fun provideAlbumsRepository(albumsApiService: AlbumsApiService): AlbumsRepository {
        return AlbumsRepositoryImpl(albumsApiService)
    }
}