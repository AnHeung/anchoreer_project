package com.anchoreer.data.db.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "movie")
data class MovieEntity(
    @PrimaryKey @ColumnInfo(name = "link")val link: String = "",
    @ColumnInfo(name = "title") val title: String?,
    @ColumnInfo(name = "actor")val actor: String? = "",
    @ColumnInfo(name = "director")val director: String? = "",
    @ColumnInfo(name = "image")val image: String? = "",
    @ColumnInfo(name = "pubDate")val pubDate: String? = "",
    @ColumnInfo(name = "subtitle")val subtitle: String? = "",
    @ColumnInfo(name = "userRating")val userRating: String? = "",
    @ColumnInfo(name = "isBookmark", defaultValue = "0") val isBookmark :Boolean? = false,
    @ColumnInfo(name = "modified_at") val modifiedAt: Long
)