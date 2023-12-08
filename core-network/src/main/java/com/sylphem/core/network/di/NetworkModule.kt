package com.sylphem.core.network.di

import android.content.Context
import coil.ImageLoader
import coil.disk.DiskCache
import com.sylphem.core.network.util.RequestHeaderInterceptor
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