package com.example.synema.controller

import com.example.synema.model.MovieModel
import com.example.synema.model.WatchlistModel
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

public interface WatchlistAPI {
    @Headers(
        "Accept: application/json"
    )
    @DELETE("/watchlist/{watchlist_id}")
    abstract fun delete_watchlist(@Path("watchlist_id") id: String, @Header("authorization") token : String) : Call<String>

    @POST("/watchlist")
    fun createWatchlist(@Body watchlist: WatchlistModel, @Header("authorization") token : String): Call<WatchlistModel>
    @Headers(
        "Accept: application/json"
    )
    @GET("movies")
    abstract fun getMovies(): Call<MovieModel?>?

    @GET("movies/discover")
    abstract fun discoverMovies(@Query("genres") genres: String): Call<List<MovieModel>>

    @GET("/watchlist")
    abstract fun read_db(@Header("authorization") token : String): Call<List<WatchlistModel>>

    @GET("/watchlist/user/{user_id}")
    abstract fun read_otherUsers_db(
        @Path("user_id") userId: String,
        @Header("authorization") token : String): Call<List<WatchlistModel>>


    @GET("/watchlist/{watchlistId}")
    abstract fun getWatchlistById(@Path("watchlistId") watchlistId: String, @Header("authorization") token : String): Call<WatchlistModel>

    @POST("/watchlist/{watchlist_id}/movies")
    @FormUrlEncoded
    abstract fun addMovieToWatchlist(
        @Path("watchlist_id") watchlistId: String,
        @Field("movie_id") movieId: String,
        @Header("authorization") token : String
    ): Call<String>

    @GET("/movies/new")
    abstract fun newMovies(): Call<List<MovieModel>>

    @DELETE("/watchlist/{watchlistId}/movies/{movieId}")
    fun deleteMovieFromWatchlist(
        @Path("watchlistId") watchlistId: String,
        @Path("movieId") movieId: String,
        @Header("authorization") token : String
    ): Call<String>

}