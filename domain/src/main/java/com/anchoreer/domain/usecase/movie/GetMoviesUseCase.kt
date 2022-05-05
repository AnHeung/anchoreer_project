package com.anchoreer.domain.usecase.movie

import com.anchoreer.domain.repository.MovieRepository
import javax.inject.Inject

class GetMoviesUseCase  @Inject constructor(private val movieRepository: MovieRepository)  {
    suspend operator fun invoke(query:String, pageCount : Int, start:Int) = movieRepository.getMovieList(query, pageCount, start)
}