package com.example.synema.controller

import com.example.synema.model.ActorModel
import com.example.synema.model.CreditsModel
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

    @GET("movie/{id}/credits")
    abstract fun getCreditsById(@Path("id") id: String): Call<List<CreditsModel>>

    @GET("actor/{id}")
    abstract fun getActorDetails(@Path("id") id: String): Call<ActorModel>

    @GET("actor/{actorId}/movies")
    abstract fun starringMovies(@Path("actorId") actorId: String): Call<List<MovieModel>>

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

    @GET("movies/{movieId}/similar")
    abstract fun similar(@Path("movieId") movieId: String): Call<List<MovieModel>>

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
        @Path("movieId") movieId: String,
        @Header("authorization") token : String
    ): Call<List<ReviewModel>>

    @GET("/user/reviews/")
    fun getOwnReviews(
        @Header("authorization") token : String
    ): Call<List<ReviewModel>>
}
