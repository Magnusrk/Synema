package com.example.synema.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class MovieModel(
    @StringRes val stringResourceId: Int,
    @DrawableRes val imageResourceId: Int
)
