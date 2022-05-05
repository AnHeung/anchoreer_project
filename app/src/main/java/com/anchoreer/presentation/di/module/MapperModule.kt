package com.anchoreer.presentation.di.module

import com.anchoreer.data.mapper.MovieLocalMapper
import com.anchoreer.data.mapper.MovieRemoteMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MapperModule {

    @Provides
    @Singleton
    fun provideMovieLocalMapper(): MovieLocalMapper{
        return MovieLocalMapper()
    }

    @Provides
    @Singleton
    fun provideMovieRemoteMapper(): MovieRemoteMapper {
        return MovieRemoteMapper()
    }
}