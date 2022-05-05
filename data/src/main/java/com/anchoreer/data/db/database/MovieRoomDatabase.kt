package com.anchoreer.data.db.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.anchoreer.data.db.dao.MovieDao
import com.anchoreer.data.db.model.MovieEntity

@Database(entities = [MovieEntity::class] , version = 1 , exportSchema = false)
abstract class MovieRoomDatabase  : RoomDatabase(){

    abstract fun movieDao(): MovieDao

    companion object {

        @Volatile
        private var INSTANCE: MovieRoomDatabase? = null

        fun getDatabase(context: Context): MovieRoomDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MovieRoomDatabase::class.java,
                    "MOVIE_DATABASE"
                )
                    .build()
                INSTANCE = instance
                instance
            }
        }

    }
}