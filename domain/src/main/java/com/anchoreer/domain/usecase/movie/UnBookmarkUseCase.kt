package com.anchoreer.domain.usecase.movie

import com.anchoreer.domain.model.MovieItem
import com.anchoreer.domain.repository.MovieRepository
import javax.inject.Inject

class UnBookmarkUseCase  @Inject constructor(private val repository: MovieRepository){
    suspend operator fun invoke(movieItem: MovieItem) = repository.unBookmark(movieItem)
}