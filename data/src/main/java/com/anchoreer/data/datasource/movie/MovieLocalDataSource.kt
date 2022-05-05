package com.anchoreer.data.datasource.movie

import com.anchoreer.domain.model.MovieItem
import kotlinx.coroutines.flow.Flow

interface MovieLocalDataSource {
    suspend fun bookmark(item: MovieItem)
    suspend fun unBookmark(item: MovieItem)
    fun getBookmarkMovieList(): Flow<List<MovieItem>>
    fun isBookmark(link: String):Flow<Boolean>
    suspend fun deleteAllMovie()
}