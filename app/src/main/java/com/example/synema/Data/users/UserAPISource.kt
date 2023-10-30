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


class UserAPISource() : UserDataSource {

    val BASE_URL = "https://0.0.0.0:3000/";

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()


    override fun LoginUser(email: String, password: String ): ApiResponse<UserModel> {

        val api = retrofit.create(UserAPI::class.java)
        val call: Call<UserModel?>? = api.userLogin(email, password);
        var result : ApiResponse<UserModel> = ApiResponse(null, true, "Couldn't login");

        call!!.enqueue(object : Callback<UserModel?> {
            override fun onResponse(call: Call<UserModel?>, response: Response<UserModel?>) {
                if (response.isSuccessful) {
                    //Login successful

                    result = ApiResponse(response.body()!!);
                }
            }

            override fun onFailure(call: Call<UserModel?>, t: Throwable) {
                //Failure

            }
        }
        )
        return result;
    }

    override fun signupUser(username: String, email: String, password: String): ApiResponse<UserModel> {
        val api = retrofit.create(UserAPI::class.java)
        val call: Call<UserModel?>? = api.userSignup(username, email, password)
        var result : ApiResponse<UserModel> = ApiResponse(null, true, "Couldn't sign up");

        call!!.enqueue(object : Callback<UserModel?> {
            override fun onResponse(call: Call<UserModel?>, response: Response<UserModel?>) {
                if (response.isSuccessful) {
                    //Login successful

                    result = ApiResponse(response.body()!!);
                }
            }

            override fun onFailure(call: Call<UserModel?>, t: Throwable) {
                //Failure

            }
        }
        )
        return result;
    }
}
