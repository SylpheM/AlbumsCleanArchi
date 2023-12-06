package com.sylphem.albums.di

import com.sylphem.albums.data.remote.AlbumsApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {

    @Singleton
    @Provides
    fun provideAlbumsApiService(): AlbumsApiService {
        return AlbumsApiService.create()
    }
}