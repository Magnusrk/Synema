package com.example.synema.controller

import com.example.synema.model.MovieModel
import com.example.synema.model.WatchlistModel
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

public interface WatchlistAPI {
    @Headers(
        "Accept: application/json"
    )
    @DELETE("/deletewatchlist/{watchlist_id}")
    abstract fun delete_wathclist(@Path("watchlist_id") id: String): Call<WatchlistModel>

    @POST("/watchlist")
    fun createWatchlist(@Body watchlist: WatchlistModel): Call<WatchlistModel>
    @Headers(
        "Accept: application/json"
    )
    @GET("movies")
    abstract fun getMovies(): Call<MovieModel?>?

    @GET("movies/discover")
    abstract fun discoverMovies(@Query("genres") genres: String): Call<List<MovieModel>>
}