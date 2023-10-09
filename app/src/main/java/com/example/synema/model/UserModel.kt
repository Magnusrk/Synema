package com.example.synema.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class ProfileModel(
    var id: String,
    var name: String,
    var email: String,
)

data class UserModel(
    var profile: ProfileModel
)
