package com.sylphem.albums.data.network.di

import com.sylphem.albums.data.network.NetworkAlbumsDataSourceImpl
import com.sylphem.albums.data.network.remote.AlbumsApiService
import com.sylphem.core.domain.repository.NetworkAlbumsDataSource
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
    fun provideAlbumsRepository(albumsApiService: AlbumsApiService): NetworkAlbumsDataSource {
        return NetworkAlbumsDataSourceImpl(albumsApiService)
    }
}