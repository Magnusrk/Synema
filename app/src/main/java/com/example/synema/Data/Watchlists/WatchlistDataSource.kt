package com.example.synema.Data.Watchlists
import com.example.synema.model.ApiResponse
import com.example.synema.model.MovieModel
import com.example.synema.model.ReviewModel
import com.example.synema.model.WatchlistModel


interface WatchlistDataSource {



    fun createWatchlist(watchlistName: String, token : String, callback: (ApiResponse<MovieModel>) -> Unit)
    fun read_db(token : String, callback: (ApiResponse<List<WatchlistModel>>) -> Unit)

    fun getAllWatchlists(token: String, callback: (ApiResponse<List<WatchlistModel>>?) -> Unit)
    fun addMovieToWatchlist(
        watchlistId: String,
        movieId: String,
        token : String,
        callback: (ApiResponse<String>) -> Unit
    )

    fun getWatchlistById(watchlistId: String,token: String, callback: (ApiResponse<WatchlistModel>) -> Unit)
}

