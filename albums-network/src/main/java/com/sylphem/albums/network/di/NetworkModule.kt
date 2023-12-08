package com.sylphem.albums.network.di

import android.content.Context
import coil.ImageLoader
import coil.disk.DiskCache
import com.sylphem.albums.network.AlbumsRepositoryImpl
import com.sylphem.albums.network.remote.AlbumsApiService
import com.sylphem.albums.network.remote.RequestHeaderInterceptor
import com.sylphem.core.domain.repository.AlbumsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

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

    @Provides
    @Singleton
    fun providesImageLoader(@ApplicationContext application: Context): ImageLoader {
        return ImageLoader.Builder(application)
            .okHttpClient {
                OkHttpClient.Builder()
                    .addNetworkInterceptor(
                        RequestHeaderInterceptor(
                            "User-Agent",
                            "AlbumsAndroidApp"
                        )
                    )
                    .build()
            }
            .crossfade(true)
            .diskCache {
                DiskCache.Builder()
                    .directory(application.cacheDir.resolve("image_cache"))
                    .maxSizeBytes(5 * 1024 * 1024) // 5 MB
                    .build()
            }
            .build()
    }
}