package com.goonandroid.appselect.servicesapi

import com.goonandroid.appselect.model.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApiInterface {
    @GET("reviews/all.json?api-key=xxx")

    suspend fun getMovieList(@Query("offset") offset : Int) : MovieResponse
}