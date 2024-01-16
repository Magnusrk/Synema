package com.example.synema.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.synema.Data.DependencyProvider
import com.example.synema.controller.AppContext

class OUsersViewModel: ViewModel() {
    val dataSource = DependencyProvider.getInstance().getUserSource();
    var followerList= mutableStateListOf<String>()


    fun getFollowerList(userid: String){

        dataSource.getFollowers(userid.toString(), AppContext.profileState.value.token){
            if (it.successful()) {
                it.getResult()?.let {followers ->
                    followerList.clear()
                    followerList.addAll(followers)
                }
            }
        }

    }
}