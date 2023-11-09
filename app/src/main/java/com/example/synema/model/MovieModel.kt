package com.example.synema.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class MovieModel(
    val id : Int,
    val poster_url : String,
    val backdrop_url : String,
    val title : String,
    val description : String,
    val rating : Number,
    val release_date : String,
)
