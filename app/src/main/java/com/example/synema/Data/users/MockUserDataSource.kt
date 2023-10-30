package com.example.synema.Data.users

import com.example.synema.R
import com.example.synema.controller.UserAPI
import com.example.synema.model.ApiResponse
import com.example.synema.model.MovieModel
import com.example.synema.model.ProfileModel
import com.example.synema.model.ReviewModel
import com.example.synema.model.UserModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MockUserDataSource() : UserDataSource {




    override fun LoginUser(email: String, password: String, callback : (ApiResponse<UserModel>) -> Unit )  {
        callback(ApiResponse(
            UserModel(
                ProfileModel(
                    "test",
                    "Chuck Norris",
                    "chuck@norris.com"
                )
            )
        ))
    }

    override fun signupUser(username: String, email: String, password: String, callback: (ApiResponse<UserModel>) -> Unit)  {
        callback(ApiResponse(
            UserModel(
                ProfileModel(
                    "test",
                    "Chuck Norris",
                    "chuck@norris.com"
                )
            )
        ))
    }
}
