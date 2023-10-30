package com.example.synema.controller

import com.example.synema.model.MovieModel
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

public interface MovieAPI {
    @Headers(
        "Accept: application/json"
    )
    @GET("movies/{id}")
    abstract fun getMovieById(@Path("id") id: String): Call<MovieModel?>?

    @Headers(
        "Accept: application/json"
    )
    @GET("movies")
    abstract fun getMovies(): Call<MovieModel?>?
}