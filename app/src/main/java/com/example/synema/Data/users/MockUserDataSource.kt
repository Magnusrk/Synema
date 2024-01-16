package com.example.synema.Data.users

import com.example.synema.model.ApiResponse
import com.example.synema.model.ProfileModel
import com.example.synema.model.UserModel
import retrofit2.converter.gson.GsonConverterFactory


class MockUserDataSource() : UserDataSource {




    override fun LoginUser(email: String, password: String, callback : (ApiResponse<UserModel>) -> Unit )  {
        callback(ApiResponse(
            UserModel(
                ProfileModel(
                    "test",
                    "Chuck Norris",
                    "chuck@norris.com",
                    "bio",
                    "",
                    "ds")

            )
        ))
    }

    override fun signupUser(username: String, email: String, password: String, callback: (ApiResponse<UserModel>) -> Unit)  {
        callback(ApiResponse(
            UserModel(
                ProfileModel(
                    "test",
                    "Chuck Norris",
                    "chuck@norris.com",
                    "bio",
                    "2",
                    "2")
            )
        ))
    }

    override fun verifyToken(token: String, callback: (ApiResponse<Boolean>) -> Unit) {
        callback(ApiResponse(true))
    }

    override fun userByUsername(
        username: String,
        token: String,
        callback: (ApiResponse<List<ProfileModel>>) -> Unit
    ) {
        TODO("Not yet implemented")
    }

    override fun userById(
        id: String,
        token: String,
        callback: (ApiResponse<ProfileModel>) -> Unit
    ) {
        TODO("Not yet implemented")
    }

    override fun editbio(
        id: String,
        bio: String,
        token: String,
        callback: (ApiResponse<Boolean>) -> Unit
    ) {
        TODO("Not yet implemented")
    }


    override fun editProfilePicture(
        id: String,
        profileModel: ProfileModel,
        token: String,
        callback: (ApiResponse<ProfileModel>) -> Unit
    ) {
        TODO("Not yet implemented")
    }

    override fun getFollowers(
        userid: String,
        token: String,
        callback: (ApiResponse<List<String>>) -> Unit
    ) {
        TODO("Not yet implemented")
    }

    override fun getFollowing(
        userid: String,
        token: String,
        callback: (ApiResponse<List<String>>) -> Unit
    ) {
        TODO("Not yet implemented")
    }




    override fun followUser(
        userid: String,
        currentUserId: String,
        token: String,
        callback: (ApiResponse<ProfileModel>) -> Unit
    ) {
        TODO("Not yet implemented")
    }

    override fun unfollowUser(
        userid: String,
        currentUserId: String,
        token: String,
        callback: (ApiResponse<ProfileModel>) -> Unit
    ) {
        TODO("Not yet implemented")
    }
}



