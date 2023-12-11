package com.sylphem.albums.ui.di

import com.sylphem.core.domain.repository.DatabaseAlbumsDataSource
import com.sylphem.core.domain.repository.NetworkAlbumsDataSource
import com.sylphem.core.domain.usecase.GetAlbumsUseCase
import com.sylphem.core.domain.usecase.GetAlbumsUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AlbumsModule {

    @Singleton
    @Provides
    fun provideGetAlbumsUseCase(
        networkAlbumsDataSource: NetworkAlbumsDataSource,
        databaseAlbumsDataSource: DatabaseAlbumsDataSource
    ): GetAlbumsUseCase {
        return GetAlbumsUseCaseImpl(networkAlbumsDataSource, databaseAlbumsDataSource)
    }
}