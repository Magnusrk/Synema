package com.example.synema.controller

import com.example.synema.model.MovieModel
import com.example.synema.model.ReviewModel
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

public interface MovieAPI {
    @Headers(
        "Accept: application/json"
    )
    @GET("movies/{id}")
    abstract fun getMovieById(@Path("id") id: String): Call<MovieModel>

    @Headers(
        "Accept: application/json"
    )
    @GET("movies")
    abstract fun getMovies(): Call<MovieModel?>?

    @GET("movies/discover")
    abstract fun discoverMovies(@Query("genres") genres: String): Call<List<MovieModel>>

    @GET("/movies/new")
    abstract fun newMovies(): Call<List<MovieModel>>

    @GET("movies/search")
    abstract fun searchMovies(@Query("query") query: String): Call<List<MovieModel>>

    @POST("/movie/{movieId}/reviews")
    fun createReviewForMovie(
        @Path("movieId") movieId: String,
        @Body reviewModel: ReviewModel,
        @Header("authorization") token : String
    ): Call<String>

    @DELETE("/movie/{movieId}/reviews")
    fun delete_Review(
        @Path("movieId") movieId: String,
        @Header("authorization") token : String
    ): Call<String>

    @GET("/movie/{movieId}/reviews")
    fun getReviewsForMovie(
        @Path("movieId") movieId: String
    ): Call<List<ReviewModel>>
}
