package com.example.synema.Data.Watchlists
import com.example.synema.model.ApiResponse
import com.example.synema.model.MovieModel
import com.example.synema.model.ReviewModel


interface WatchlistDataSource {



    fun createWatchlist(watchlistName: String, callback: (ApiResponse<MovieModel>) -> Unit)
}
