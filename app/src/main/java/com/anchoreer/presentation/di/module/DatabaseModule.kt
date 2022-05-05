package com.anchoreer.presentation.di.module

import android.content.Context
import androidx.room.Room
import com.anchoreer.data.db.database.MovieRoomDatabase
import com.anchoreer.presentation.utils.MOVIE_DATABASE
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context) = MovieRoomDatabase.getDatabase(context)

    @Provides
    fun provideMovieDao(appDatabase: MovieRoomDatabase) = appDatabase.movieDao()
}