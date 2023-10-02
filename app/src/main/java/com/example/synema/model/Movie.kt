package com.example.synema.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Movie(
    @StringRes val stringResourceId: Int,
    @DrawableRes val imageResourceId: Int
)
