package com.goonandroid.appselect.servicesapi

import com.goonandroid.appselect.MovieResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApiInterface {
    @GET("reviews/all.json?api-key=BQ6rTcziiGJE7GHuqNS0O6JQmArUIept")

    fun getMovieList(@Query("offset") offset : Int) : Call<MovieResponse>
}