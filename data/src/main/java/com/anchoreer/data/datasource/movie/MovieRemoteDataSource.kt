package com.anchoreer.data.datasource.movie

import com.anchoreer.domain.common.Result
import com.anchoreer.domain.model.MovieItem
import kotlinx.coroutines.flow.Flow

interface MovieRemoteDataSource {
    suspend fun getMovies(query:String, pageCount: Int, start:Int): Flow<Result<List<MovieItem>>>

}