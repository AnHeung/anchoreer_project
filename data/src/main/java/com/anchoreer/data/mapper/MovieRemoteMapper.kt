package com.anchoreer.data.mapper

import com.anchoreer.data.api.model.MovieResultItem
import com.anchoreer.data.utils.changePipe
import com.anchoreer.data.utils.deleteBracket
import com.anchoreer.domain.model.MovieItem

class MovieRemoteMapper {

    fun toMovieResultListToMovieItemList(movieResultItem: MovieResultItem) :List<MovieItem>{
        return movieResultItem.items?.map {
            val title = it.title?.deleteBracket()
            val director = it.director?.changePipe( "").deleteBracket()
            val actor = it.actor?.changePipe(",").deleteBracket()
           MovieItem(title, actor, director, it.image, it.link, it.pubDate, it.subtitle, it.userRating , false)
        }?: listOf()
    }
}