package com.anchoreer.data.mapper

import com.anchoreer.data.db.model.MovieEntity
import com.anchoreer.domain.model.MovieItem

class MovieLocalMapper {


    // 서버에서 가져온 API 목록에 기존 로컬에 저장되있는 즐겨찾기 목록을 합쳐서 하나의 리스트로 만듬
    fun toBookmarkList(
        remote: List<MovieItem>,
        bookmark: List<MovieItem>
    ): List<MovieItem> {

        val newList = remote.fold(mutableListOf<MovieItem>()) { acc, movieItem ->

            val commonElements = bookmark.find { bookmarkItem ->
                bookmarkItem.link == movieItem.link
            }
            if (commonElements != null) acc.add(commonElements)
            else acc.add(movieItem)
            acc
        }
        return newList
    }

    fun toMovieEntity(movieItem: MovieItem): MovieEntity {
        return movieItem.run {
            MovieEntity(
                link ?: "", title, actor, director, image, pubDate, subtitle, userRating, true,System.currentTimeMillis()
            )
        }
    }

    fun toMovieItem(movieEntity: MovieEntity): MovieItem {
        return movieEntity.run {
            MovieItem(
                title,
                actor,
                director,
                image,
                link,
                pubDate,
                subtitle,
                userRating,
                isBookmark ?: false
            )
        }
    }
}