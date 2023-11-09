package com.example.synema.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class ProfileModel(
    val id: String,
    val name: String,
    val email: String,
    var bio: String,
)

data class UserModel(
    val profile: ProfileModel
)
