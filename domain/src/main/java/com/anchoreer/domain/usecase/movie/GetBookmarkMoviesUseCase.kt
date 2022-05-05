package com.anchoreer.domain.usecase.movie

import com.anchoreer.domain.repository.MovieRepository
import javax.inject.Inject

class GetBookmarkMoviesUseCase  @Inject constructor(private val movieRepository: MovieRepository) {
     operator fun invoke() = movieRepository.getBookmarkMovieList()
}