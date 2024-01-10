package com.example.synema.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes


data class ReviewModel(
    val reviewText : String,
    val rating : Int,
    val movieid : String,
    val username: String,
    val userid: String,
)
