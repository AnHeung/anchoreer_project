package com.anchoreer.presentation.di.module

import com.anchoreer.data.datasource.movie.MovieLocalDataSource
import com.anchoreer.data.datasource.movie.MovieRemoteDataSource
import com.anchoreer.data.repository.MovieRepositoryImpl
import com.anchoreer.domain.repository.MovieRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideMovieRepository(
        movieLocalDataSource: MovieLocalDataSource,
        movieRemoteDataSource: MovieRemoteDataSource,
    ): MovieRepository {
        return MovieRepositoryImpl(movieLocalDataSource, movieRemoteDataSource)
    }
}