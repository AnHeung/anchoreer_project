package com.anchoreer.data.datasource.movie

import com.anchoreer.data.db.dao.MovieDao
import com.anchoreer.data.mapper.MovieLocalMapper
import com.anchoreer.domain.model.MovieItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class MovieLocalDataSourceImpl @Inject constructor(
    private val movieDao: MovieDao,
    private val movieLocalMapper: MovieLocalMapper
) : MovieLocalDataSource {

    companion object {
        val DEFAULT = Dispatchers.Default
    }


    override suspend fun bookmark(item: MovieItem) {
        return movieDao.addBookmark(movieLocalMapper.toMovieEntity(item))
    }

    override suspend fun unBookmark(item: MovieItem) {
        return movieDao.deleteBookmarkMovieByLink(item.link ?: "")
    }

    override fun getBookmarkMovieList(): Flow<List<MovieItem>> =
        movieDao.getBookmarkMovieList()
            .map { list ->
                list.map { item ->
                    movieLocalMapper.toMovieItem(item)
                }
            }.catch {  }
            .flowOn(DEFAULT)


    override fun isBookmark(link: String) = movieDao.isBookmark(link)

    override suspend fun deleteAllMovie() = movieDao.deleteAll()

}