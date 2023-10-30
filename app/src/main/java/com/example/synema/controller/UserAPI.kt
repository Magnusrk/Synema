package com.example.synema.controller


import com.example.synema.model.UserModel
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path

public interface UserAPI {
    @Headers(
        "Accept: application/json"
    )
    @POST("user/login")
    @FormUrlEncoded
    fun userLogin(
        @Field("email") email: String,
        @Field("password") password: String,
    ) : Call<UserModel>

    @Headers(
        "Accept: application/json"
    )
    @POST("user/signup")
    @FormUrlEncoded
    fun userSignup(
        @Field("username") username: String,
        @Field("email") email: String,
        @Field("password") password: String,
    ) : Call<UserModel?>?
}
