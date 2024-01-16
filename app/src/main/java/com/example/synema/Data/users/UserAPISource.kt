package com.example.synema.Data.users

import android.util.Log
import com.example.synema.controller.UserAPI
import com.example.synema.model.ApiResponse
import com.example.synema.model.ProfileModel
import com.example.synema.model.UserModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class UserAPISource() : UserDataSource {
    //private val BASE_URL = "http://192.168.0.107:8000"
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
                    if (response.code() == 200) {
                        Log.d("Main", "success!" + response.body().toString())
                        callback(ApiResponse(response.body()!!))
                    }
                } else if(response.code() == 404){
                    callback(ApiResponse(null, true, "User does not exist"))
                } else{
                    callback(ApiResponse(null, true, "Couldn't log in"))
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
                } else if(response.code() == 306){
                    callback(ApiResponse(null, true, "User already exists"))
                }else{
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

    override fun verifyToken(token: String, callback: (ApiResponse<Boolean>) -> Unit) {
        val api = retrofit.create(UserAPI::class.java)
        val call: Call<Boolean> = api.verifyToken(token)


        call.enqueue(object : Callback<Boolean> {
            override fun onResponse(call: Call<Boolean?>, response: Response<Boolean>) {
                if (response.isSuccessful) {
                    callback(ApiResponse(response.body()!!))
                } else{
                    callback(ApiResponse(null, true, "Couldn't check token"))
                }
            }

            override fun onFailure(call: Call<Boolean>, t: Throwable) {
                //Failure
                callback(ApiResponse(null, true, "Couldn't check token"))

            }
        }
        )
    }

    override fun userByUsername(
        username: String,
        token: String,
        callback: (ApiResponse<List<ProfileModel>>) -> Unit
    ) {
        val api = retrofit.create(UserAPI::class.java)
        val call: Call<List<ProfileModel>> = api.user_by_username(username,token);

        call.enqueue(object: Callback<List<ProfileModel>> {
            override fun onResponse(call: Call<List<ProfileModel>>, response: Response<List<ProfileModel>>) {
                if(response.isSuccessful) {
                    if (response.code() == 200) {
                        Log.d("Main", "success!" + response.body().toString())
                        callback(ApiResponse(response.body()!!))
                    }
                } else if(response.code() == 404){
                    callback(ApiResponse(null, true, "User does not exist"))
                } else{
                    callback(ApiResponse(null, true, "Couldn't log in"))
                }
            }

            override fun onFailure(call: Call<List<ProfileModel>>, t: Throwable) {
                Log.e("Main", "Login failed " + t.message.toString())
                callback(ApiResponse(null, true, t.message!!));

            }
        })
    }

    override fun userById(
        id: String,
        token: String,
        callback: (ApiResponse<ProfileModel>) -> Unit
    ) {
        val api = retrofit.create(UserAPI::class.java)
        val call: Call<ProfileModel> = api.user_by_id(id,token);

        call.enqueue(object: Callback<ProfileModel> {
            override fun onResponse(call: Call<ProfileModel>, response: Response<ProfileModel>) {
                if(response.isSuccessful) {
                    if (response.code() == 200) {
                        Log.d("Main", "success!" + response.body().toString())
                        callback(ApiResponse(response.body()!!))
                    }
                } else if(response.code() == 404){
                    callback(ApiResponse(null, true, "User does not exist"))
                } else{
                    callback(ApiResponse(null, true, "Couldn't log in"))
                }
            }

            override fun onFailure(call: Call<ProfileModel>, t: Throwable) {
                Log.e("Main", "Login failed " + t.message.toString())
                callback(ApiResponse(null, true, t.message!!));

            }
        })
    }

    override fun editbio(
        id: String,
        bio: String,
        token: String,
        callback: (ApiResponse<Boolean>) -> Unit
    ) {
        val api = retrofit.create(UserAPI::class.java)
        val call: Call<Boolean> = api.editbio(id, ProfileModel("","","",bio,"",""),token);

        call.enqueue(object: Callback<Boolean> {
            override fun onResponse(call: Call<Boolean>, response: Response<Boolean>) {
                if(response.isSuccessful) {
                    if (response.code() == 200) {
                        callback(ApiResponse(true))
                    }
                } else if(response.code() == 404){
                    callback(ApiResponse(null, true, "User does not exist"))
                } else{
                    callback(ApiResponse(null, true, "Couldn't log in"))
                }
            }

            override fun onFailure(call: Call<Boolean>, t: Throwable) {
                Log.e("Main", "Login failed " + t.message.toString())
                callback(ApiResponse(null, true, t.message!!));

            }
        })
    }
    override fun editusername(
        id: String,
        name: String,
        token: String,
        callback: (ApiResponse<Boolean>) -> Unit
    ) {
        val api = retrofit.create(UserAPI::class.java)
        val call: Call<Boolean> = api.editusername(id, ProfileModel("","","",name,"",""),token);

        call.enqueue(object: Callback<Boolean> {
            override fun onResponse(call: Call<Boolean>, response: Response<Boolean>) {
                if(response.isSuccessful) {
                    if (response.code() == 200) {
                        callback(ApiResponse(true))
                    }
                } else if(response.code() == 404){
                    callback(ApiResponse(null, true, "User does not exist"))
                } else{
                    callback(ApiResponse(null, true, "Couldn't log in"))
                }
            }

            override fun onFailure(call: Call<Boolean>, t: Throwable) {
                Log.e("Main", "Login failed " + t.message.toString())
                callback(ApiResponse(null, true, t.message!!));

            }
        })
    }

    override fun editProfilePicture(
        id: String,
        profilePicture: String,
        token: String,
        callback: (ApiResponse<ProfileModel>) -> Unit
    ) {
        val api = retrofit.create(UserAPI::class.java)
        val call: Call<ProfileModel> = api.editProfilePicture(id,ProfileModel("","","","","",""),token);

        call.enqueue(object: Callback<ProfileModel> {
            override fun onResponse(call: Call<ProfileModel>, response: Response<ProfileModel>) {
                if(response.isSuccessful) {
                    if (response.code() == 200) {
                        Log.d("Main", "success!" + response.body().toString())
                        callback(ApiResponse(response.body()!!))
                    }
                } else if(response.code() == 404){
                    callback(ApiResponse(null, true, "User does not exist"))
                } else{
                    callback(ApiResponse(null, true, "Couldn't log in"))
                }
            }

            override fun onFailure(call: Call<ProfileModel>, t: Throwable) {
                Log.e("Main", "Login failed " + t.message.toString())
                callback(ApiResponse(null, true, t.message!!));

            }
        })
    }

    override fun getFollowers(
        userid: String,
        token: String,
        callback: (ApiResponse<List<String>>) -> Unit
    ) {
        val api = retrofit.create(UserAPI::class.java)
        val call: Call<List<String>> = api.getFollowers(userid,token);

        call.enqueue(object: Callback<List<String>> {
            override fun onResponse(call: Call<List<String>>, response: Response<List<String>>) {
                if(response.isSuccessful) {
                    if (response.code() == 200) {
                        Log.d("Main", "success!" + response.body().toString())
                        callback(ApiResponse(response.body()!!))
                    }
                } else if(response.code() == 404){
                    callback(ApiResponse(null, true, "User does not exist"))
                } else{
                    callback(ApiResponse(null, true, "Couldn't log in"))
                }
            }

            override fun onFailure(call: Call<List<String>?>, t: Throwable) {
                Log.e("Main", "Login failed " + t.message.toString())
                callback(ApiResponse(null, true, t.message!!));

            }
        })
    }

    override fun getFollowing(
        userid: String,
        token: String,
        callback: (ApiResponse<List<String>>) -> Unit
    ) {
        val api = retrofit.create(UserAPI::class.java)
        val call: Call<List<String>> = api.getFollowing(userid,token);

        call.enqueue(object: Callback<List<String>> {
            override fun onResponse(call: Call<List<String>>, response: Response<List<String>>) {
                if(response.isSuccessful) {
                    if (response.code() == 200) {
                        Log.d("Main", "success!" + response.body().toString())
                        callback(ApiResponse(response.body()!!))
                    }
                } else if(response.code() == 404){
                    callback(ApiResponse(null, true, "User does not exist"))
                } else{
                    callback(ApiResponse(null, true, "Couldn't log in"))
                }
            }

            override fun onFailure(call: Call<List<String>>, t: Throwable) {
                Log.e("Main", "Login failed " + t.message.toString())
                callback(ApiResponse(null, true, t.message!!));

            }
        })
    }

    override fun followUser(
        userid: String,
        currentUserId: String,
        token: String,
        callback: (ApiResponse<ProfileModel>) -> Unit
    ) {

        val api = retrofit.create(UserAPI::class.java)
        val call: Call<ProfileModel> = api.followUser(userid,currentUserId,token);

        call.enqueue(object: Callback<ProfileModel> {
            override fun onResponse(call: Call<ProfileModel>, response: Response<ProfileModel>) {
                if(response.isSuccessful) {
                    if (response.code() == 200) {
                        Log.d("Main", "success!" + response.body().toString())
                        callback(ApiResponse(response.body()!!))
                    }
                } else if(response.code() == 404){
                    callback(ApiResponse(null, true, "User does not exist"))
                } else{
                    callback(ApiResponse(null, true, "Couldn't log in"))
                }
            }

            override fun onFailure(call: Call<ProfileModel>, t: Throwable) {
                Log.e("Main", "Login failed " + t.message.toString())
                callback(ApiResponse(null, true, t.message!!));

            }
        })    }


    override fun unfollowUser(
        userid: String,
        currentUserId: String,
        token: String,
        callback: (ApiResponse<ProfileModel>) -> Unit
    ) {

        val api = retrofit.create(UserAPI::class.java)
        val call: Call<ProfileModel> = api.unfollowUser(userid,currentUserId,token);

        call.enqueue(object: Callback<ProfileModel> {
            override fun onResponse(call: Call<ProfileModel>, response: Response<ProfileModel>) {
                if(response.isSuccessful) {
                    if (response.code() == 200) {
                        Log.d("Main", "success!" + response.body().toString())
                        callback(ApiResponse(response.body()!!))
                    }
                } else if(response.code() == 404){
                    callback(ApiResponse(null, true, "User does not exist"))
                } else{
                    callback(ApiResponse(null, true, "Couldn't log in"))
                }
            }

            override fun onFailure(call: Call<ProfileModel>, t: Throwable) {
                Log.e("Main", "Login failed " + t.message.toString())
                callback(ApiResponse(null, true, t.message!!));

            }
        })    }
}