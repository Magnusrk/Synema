package com.example.synema.controller


import com.example.synema.model.ProfileModel
import com.example.synema.model.UserModel
import com.example.synema.model.WatchlistModel
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path

public interface UserAPI {
    @Headers(
        "Accept: application/json",
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

    @POST("token/verify")
    @FormUrlEncoded
    fun verifyToken(
        @Field("token") token: String,
    ) : Call<Boolean>


    @GET("/user/{username}")
    fun user_by_username(
        @Path("username") username: String,
        @Header("authorization") token : String): Call<List<ProfileModel>>

    @GET("/user/profile/{id}")
    fun user_by_id(
        @Path("id") username: String,
        @Header("authorization") token : String): Call<ProfileModel>
    @POST("/user/editbio/{id}")
    fun editbio(
    @Path("id") id: String,
    @Body profileModel: ProfileModel,
    @Header("authorization") token : String): Call<ProfileModel>

    @POST("/user/editProfilePicture/{id}")
    fun editProfilePicture(
        @Path("id") id: String,
        @Body profileModel: ProfileModel,
        @Header("authorization") token : String): Call<ProfileModel>
}

