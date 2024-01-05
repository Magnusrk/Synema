package com.example.synema.model

data class WatchlistModel (
    val name: String,
    val watchlist_id: String,
    val userid: String,
    val movieIds: List<String>
)