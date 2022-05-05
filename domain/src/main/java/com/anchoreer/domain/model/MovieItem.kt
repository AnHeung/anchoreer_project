package com.anchoreer.domain.model

data class MovieItem(
    val title: String? = "",
    val actor: String? = "",
    val director: String? = "",
    val image: String? = "",
    val link: String? = "",
    val pubDate: String? = "",
    val subtitle: String? = "",
    val userRating: String? = "",
    val isBookmark: Boolean = false,
)