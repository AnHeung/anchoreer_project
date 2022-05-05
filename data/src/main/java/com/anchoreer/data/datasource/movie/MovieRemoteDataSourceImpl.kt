package com.anchoreer.data.datasource.movie

import com.anchoreer.data.api.MovieApiService
import com.anchoreer.data.mapper.MovieRemoteMapper
import com.anchoreer.domain.common.Result
import com.anchoreer.domain.model.MovieItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class MovieRemoteDataSourceImpl @Inject constructor(
    private val movieApiService: MovieApiService,
    private val mapper: MovieRemoteMapper
) : MovieRemoteDataSource {

    companion object {
        val IO = Dispatchers.IO
    }

    override suspend fun getMovies(
        query: String,
        pageCount: Int,
        start: Int
    ): Flow<Result<List<MovieItem>>> = flow {
        val response = movieApiService.getMovieList(query, pageCount, start)
        if (response.isSuccessful) {
            emit(Result.Success(mapper.toMovieResultListToMovieItemList(response.body()!!)))
        } else {
            emit(Result.Error(Exception(response.message())))
        }
    }.flowOn(IO)
}