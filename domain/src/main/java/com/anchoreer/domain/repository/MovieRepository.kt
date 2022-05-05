package com.anchoreer.domain.repository

import com.anchoreer.domain.common.Result
import com.anchoreer.domain.model.MovieItem
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    suspend fun getMovieList(query:String, pageCount : Int, start:Int): Flow<Result<List<MovieItem>>>
    fun getBookmarkMovieList(): Flow<List<MovieItem>>
    suspend fun bookmark(item: MovieItem)
    suspend fun unBookmark(item: MovieItem)
    fun isBookmark(link: String):Flow<Boolean>
    suspend fun deleteAllMovie()
}