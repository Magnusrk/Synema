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
    @POST("/user/editusername/{userid}")
    fun editusername(
    @Path("userid") id: String,
    @Body profileModel: ProfileModel,
    @Header("authorization") token : String): Call<Boolean>

    @POST("/user/editbio/{userid}")
    fun editbio(
    @Path("userid") id: String,
    @Body profileModel: ProfileModel,
    @Header("authorization") token : String): Call<Boolean>

    @POST("/user/editProfilePicture/{id}")
    fun editProfilePicture(
        @Path("id") id: String,
        @Body profileModel: ProfileModel,
        @Header("authorization") token : String): Call<ProfileModel>

    @GET("/user/{userid}/followers")
    fun getFollowers(
        @Path("userid") id: String,
        @Header("authorization") token : String): Call<List<String>>
    @GET("/user/{userid}/following")
    fun getFollowing(
        @Path("userid") id: String,
        @Header("authorization") token : String): Call<List<String>>

    @POST("/user/{currentUserId}/follow/{userid}")
    fun followUser(
        @Path("userid") id: String,
        @Path("currentUserId") currentUserId: String,
        @Header("authorization") token : String): Call<ProfileModel>

    @POST("/user/{currentUserId}/unfollow/{userid}")
    fun unfollowUser(
        @Path("userid") id: String,
        @Path("currentUserId") currentUserId: String,
        @Header("authorization") token : String): Call<ProfileModel>
}

