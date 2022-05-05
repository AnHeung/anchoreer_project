package com.anchoreer.presentation.di.module

import com.anchoreer.data.api.MovieApiService
import com.anchoreer.data.datasource.movie.MovieLocalDataSource
import com.anchoreer.data.datasource.movie.MovieLocalDataSourceImpl
import com.anchoreer.data.datasource.movie.MovieRemoteDataSource
import com.anchoreer.data.datasource.movie.MovieRemoteDataSourceImpl
import com.anchoreer.data.db.dao.MovieDao
import com.anchoreer.data.mapper.MovieLocalMapper
import com.anchoreer.data.mapper.MovieRemoteMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {

    @Provides
    @Singleton
    fun provideMovieLocalDataSource(movieDao: MovieDao, movieLocalMapper: MovieLocalMapper
    ) : MovieLocalDataSource {
        return MovieLocalDataSourceImpl(movieDao, movieLocalMapper)
    }

    @Provides
    @Singleton
    fun provideMovieRemoteDataSource(movieApiService: MovieApiService, mapper: MovieRemoteMapper
    ) : MovieRemoteDataSource {
        return MovieRemoteDataSourceImpl(movieApiService,mapper)
    }
}