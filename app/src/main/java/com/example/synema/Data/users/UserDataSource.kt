package com.example.synema.Data.users

import com.example.synema.R
import com.example.synema.model.ApiResponse
import com.example.synema.model.MovieModel
import com.example.synema.model.ProfileModel
import com.example.synema.model.ReviewModel
import com.example.synema.model.UserModel


interface UserDataSource {
    fun LoginUser(email: String, password: String, callback: (ApiResponse<UserModel>) -> Unit);

    fun signupUser(
        username: String,
        email: String,
        password: String,
        callback: (ApiResponse<UserModel>) -> Unit
    );

    fun verifyToken(token: String, callback: (ApiResponse<Boolean>) -> Unit);

    fun userByUsername(
        username: String,
        token: String,
        callback: (ApiResponse<List<ProfileModel>>) -> Unit
    )

    fun userById(id: String, token: String, callback: (ApiResponse<ProfileModel>) -> Unit)
}
