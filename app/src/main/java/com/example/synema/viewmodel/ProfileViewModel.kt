package com.example.synema.viewmodel

import android.content.Context
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.synema.Data.DataStore.DataStoreManager
import com.example.synema.Data.DependencyProvider
import com.example.synema.controller.AppContext
import com.example.synema.model.ProfileModel
import com.example.synema.model.ReviewModel
import com.example.synema.model.UserModel
import kotlinx.coroutines.launch

class ProfileViewModel : ViewModel() {

    var context = AppContext.getInstance()
    val source = DependencyProvider.getInstance().getUserSource()
    var followerCount = mutableIntStateOf(0)
    val profileState = AppContext.getInstance().getProfileState().value


    fun init(profileModel: ProfileModel){
        getFollowers(profileModel)
    }


    fun logout(c: Context) {
         context.setProfileState(ProfileModel("","","","","",""));
         viewModelScope.launch {
             DataStoreManager(c).clearDataStore().let {
                 context.getNav().navigate("login")
             }
         }


    }

    fun EditProfile(){
        context.getNav().navigate("editprofile")
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