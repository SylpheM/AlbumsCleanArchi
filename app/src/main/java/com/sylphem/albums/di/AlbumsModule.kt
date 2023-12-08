package com.sylphem.albums.di

import com.sylphem.albums.data.local.database.DatabaseAlbumsDataSourceImpl
import com.sylphem.core.domain.repository.DatabaseAlbumsDataSource
import com.sylphem.core.domain.repository.NetworkAlbumsDataSource
import com.sylphem.core.domain.usecase.GetAlbumsUseCase
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
        return GetAlbumsUseCase(networkAlbumsDataSource, databaseAlbumsDataSource)
    }

    @Singleton
    @Provides
    fun providesDatabaseAlbumsDataSource(): DatabaseAlbumsDataSource = DatabaseAlbumsDataSourceImpl()
}