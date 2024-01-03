package com.example.synema.Data.Watchlists
import com.example.synema.model.ApiResponse
import com.example.synema.model.MovieModel
import com.example.synema.model.ReviewModel
import com.example.synema.model.WatchlistModel


interface WatchlistDataSource {



    fun createWatchlist(watchlistName: String, callback: (ApiResponse<MovieModel>) -> Unit)
    fun read_db(callback: (ApiResponse<List<WatchlistModel>>) -> Unit)
}

