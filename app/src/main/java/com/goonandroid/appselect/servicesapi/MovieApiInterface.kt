package com.goonandroid.appselect.servicesapi

import com.goonandroid.appselect.MovieResponse
import retrofit2.Call
import retrofit2.http.GET

interface MovieApiInterface {
    @GET("reviews/all.json?api-key=BQ6rTcziiGJE7GHuqNS0O6JQmArUIept")

    fun getMovieList(): Call<MovieResponse>
}