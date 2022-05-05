package com.anchoreer.data.api

import com.anchoreer.data.INITIAL_PAGE_INDEX
import com.anchoreer.data.NETWORK_PAGE_SIZE
import com.anchoreer.data.api.model.MovieResultItem
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApiService {

    @GET("v1/search/movie.json")
    suspend fun getMovieList(
        @Query("query") query: String,
        @Query("display") pageCount: Int = NETWORK_PAGE_SIZE,
        @Query("start") page: Int? = INITIAL_PAGE_INDEX,
    ): Response<MovieResultItem>
}