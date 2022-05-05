package com.anchoreer.data.repository

import com.anchoreer.data.datasource.movie.MovieLocalDataSource
import com.anchoreer.data.datasource.movie.MovieRemoteDataSource
import com.anchoreer.domain.common.Result
import com.anchoreer.domain.model.MovieItem
import com.anchoreer.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val movieLocalDataSource: MovieLocalDataSource,
    private val movieRemoteDataSource: MovieRemoteDataSource,
) : MovieRepository {

    override suspend fun getMovieList(query:String, pageCount : Int, start:Int) : Flow<Result<List<MovieItem>>> = movieRemoteDataSource.getMovies(query, pageCount, start)

    override suspend fun bookmark(item: MovieItem) = movieLocalDataSource.bookmark(item)

    override suspend fun unBookmark(item: MovieItem) = movieLocalDataSource.unBookmark(item)

    override fun isBookmark(link: String):Flow<Boolean> = movieLocalDataSource.isBookmark(link)

    override fun getBookmarkMovieList(): Flow<List<MovieItem>> = movieLocalDataSource.getBookmarkMovieList()

    override suspend fun deleteAllMovie() = movieLocalDataSource.deleteAllMovie()

}