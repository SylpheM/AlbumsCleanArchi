package com.sylphem.albums.network.di

import com.sylphem.albums.network.AlbumsRepositoryImpl
import com.sylphem.albums.network.remote.AlbumsApiService
import com.sylphem.core.domain.repository.AlbumsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AlbumsNetworkModule {

    @Singleton
    @Provides
    fun provideAlbumsApiService(): AlbumsApiService {
        return AlbumsApiService.create()
    }

    @Provides
    @Singleton
    fun provideAlbumsRepository(albumsApiService: AlbumsApiService): AlbumsRepository {
        return AlbumsRepositoryImpl(albumsApiService)
    }
}