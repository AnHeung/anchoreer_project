package com.anchoreer.data.api.model
data class MovieResultItem(
    val display: Int? = 0,
    val items: List<Item>? = listOf(),
    val lastBuildDate: String? = "",
    val start: Int? = 0,
    val total: Int? = 0
)

data class Item(
    val actor: String? = "",
    val director: String? = "",
    val image: String? = "",
    val link: String? = "",
    val pubDate: String? = "",
    val subtitle: String? = "",
    val title: String? = "",
    val userRating: String? = ""
)

data class Meta(
    val is_end: Boolean,
    val pageable_count: Int,
    val total_count: Int
)