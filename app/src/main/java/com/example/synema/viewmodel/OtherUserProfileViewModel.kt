package com.example.synema.viewmodel

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.synema.Data.DataStore.DataStoreManager
import com.example.synema.Data.DependencyProvider
import com.example.synema.controller.AppContext
import com.example.synema.model.ProfileModel
import com.example.synema.model.ReviewModel
import com.example.synema.model.UserModel
import kotlinx.coroutines.launch

class OtherUserProfileViewModel : ViewModel() {

    var context = AppContext.getInstance()
    val source = DependencyProvider.getInstance().getUserSource()
    var followerList= mutableStateListOf<String>()
    val movieSource = DependencyProvider.getInstance().getMovieSource();
    var followerCount = mutableIntStateOf(0)
    val profileState = AppContext.getInstance().getProfileState().value
    var user = mutableStateOf(
            ProfileModel(
                "",
                "",
                "",
                "Loading...",
                "Loading...",
                "",
            )
        )

    var reviewList = mutableStateListOf<ReviewModel>(

    )


    fun getOtherUserReviews(userid : String){
        movieSource.getOtherUserReviews(userid.toString(), profileState.token) {
            if (it.successful()) {
                it.getResult()?.let { reviewModel ->
                    reviewList.clear()
                    reviewList.addAll(reviewModel)
                }
            }
        }
    }

    fun init(userid : String){
        source.userById(userid.toString(), profileState.token) {
            it.getResult()?.let { profileModel ->
                user.value = it.getResult()!!
                getFollowers(user.value)
            }
        }

        getOtherUserReviews(userid)
        getFollowerList(userid)

    }




    fun getFollowerList(userid: String){

        source.getFollowers(userid.toString(), AppContext.profileState.value.token){
            if (it.successful()) {
                it.getResult()?.let {followers ->
                    followerList.clear()
                    followerList.addAll(followers)
                }
            }
        }

    }

    fun getFollowers(profileModel: ProfileModel) {
        source.getFollowers(profileModel.id, profileState.token) {
            if (it.successful()) {
                it.getResult()?.let { string ->
                    followerCount.value = string.size
                }
            }
        }

    }
}