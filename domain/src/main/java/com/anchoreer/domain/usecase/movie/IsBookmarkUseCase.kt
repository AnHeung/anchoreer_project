package com.anchoreer.domain.usecase.movie

import com.anchoreer.domain.repository.MovieRepository
import javax.inject.Inject

class IsBookmarkUseCase @Inject constructor(private val repository: MovieRepository){
     operator fun invoke(link:String) = repository.isBookmark(link)
}