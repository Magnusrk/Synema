package com.example.synema.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes


data class ReviewModel(
    val owner : ProfileModel,
    val reviewText : String,
    val rating : Int,
    val movie : MovieModel
)
