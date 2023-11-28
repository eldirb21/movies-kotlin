package com.eldiro.movieskotlin.services

import com.eldiro.movieskotlin.models.MovieResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServices {
    @GET("movie/popular")
    fun getDataFromApi(@Query("api_key") apiKey: String): Call<MovieResponse>


}