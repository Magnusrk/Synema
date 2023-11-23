package com.example.synema.Data.users

import android.util.Log
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


class UserAPISource() : UserDataSource {

    private val BASE_URL = "https://cwjtedqahp.eu10.qoddiapp.com/";

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()


    override fun LoginUser(email: String, password: String, callback : (ApiResponse<UserModel>) -> Unit ) {

        val api = retrofit.create(UserAPI::class.java)
        val call: Call<UserModel> = api.userLogin(email, password);

        call.enqueue(object: Callback<UserModel?> {
            override fun onResponse(call: Call<UserModel?>, response: Response<UserModel?>) {
                if(response.isSuccessful) {
                    Log.d("Main", "success!" + response.body().toString())
                    callback(ApiResponse(response.body()!!))
                }
                else{
                    callback(ApiResponse(null, true, "Couldn't sign up"))
                }
            }

            override fun onFailure(call: Call<UserModel?>, t: Throwable) {
                Log.e("Main", "Login failed " + t.message.toString())
                callback(ApiResponse(null, true, t.message!!));

            }
        })
    }

    override fun signupUser(username: String, email: String, password: String, callback: (ApiResponse<UserModel>) -> Unit){
        val api = retrofit.create(UserAPI::class.java)
        val call: Call<UserModel?>? = api.userSignup(username, email, password)


        call!!.enqueue(object : Callback<UserModel?> {
            override fun onResponse(call: Call<UserModel?>, response: Response<UserModel?>) {
                if (response.isSuccessful) {

                    callback(ApiResponse(response.body()!!))
                } else{
                    callback(ApiResponse(null, true, "Couldn't sign up"))
                }
            }

            override fun onFailure(call: Call<UserModel?>, t: Throwable) {
                //Failure
                callback(ApiResponse(null, true, "Couldn't sign up"))

            }
        }
        )
    }
}