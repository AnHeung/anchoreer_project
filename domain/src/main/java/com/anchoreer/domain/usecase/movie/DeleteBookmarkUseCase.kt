package com.anchoreer.domain.usecase.movie

import com.anchoreer.domain.repository.MovieRepository
import javax.inject.Inject

class DeleteBookmarkUseCase @Inject constructor(private val repository: MovieRepository) {
    suspend operator fun invoke() = repository.deleteAllMovie()
}