package com.example.synema.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class ProfileModel(
    val id: String = "",
    var name: String,
    val email: String = "",
    var bio: String,
    var token : String = "none",
    var profilePicture: String,
    var followers: List<String> = listOf(),
    var following: List<String> = listOf()
)

data class UserModel(
    val profile: ProfileModel
)