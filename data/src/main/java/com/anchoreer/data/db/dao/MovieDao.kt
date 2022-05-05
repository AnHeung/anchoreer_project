package com.anchoreer.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.anchoreer.data.db.model.MovieEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {

    @Query("SELECT * FROM movie")
    fun getMovieList(): Flow<List<MovieEntity>>

    @Query("SELECT * FROM movie WHERE isBookmark = 1")
    fun getBookmarkMovieList(): Flow<List<MovieEntity>>

    @Query("SELECT isBookmark FROM movie WHERE link = :link")
    fun isBookmark(link:String): Flow<Boolean>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBookmarkMovieList(histories: List<MovieEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addBookmark(entity: MovieEntity)

    @Query("DELETE FROM movie WHERE link = :link")
    suspend fun deleteBookmarkMovieByLink(link: String)

    @Query("DELETE FROM movie")
    suspend fun  deleteAll();

}