package com.goonandroid.appselect.servicesapi

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MovieApiService {
    companion object{
        private const val BASE_URL = "https://api.nytimes.com/svc/movies/v2/"
        private var retrofit: Retrofit? = null

        fun getInstance() : Retrofit{
            if(retrofit == null){
                retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()

            }
            return retrofit!!

        }
    }
}