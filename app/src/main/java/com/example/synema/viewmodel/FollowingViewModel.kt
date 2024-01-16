package com.example.synema.viewmodel

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.example.synema.Data.DataStore.DataStoreManager
import com.example.synema.Data.DependencyProvider
import com.example.synema.controller.AppContext
import com.example.synema.model.MovieModel
import com.example.synema.model.ProfileModel
import kotlinx.coroutines.launch

class FollowingViewModel : ViewModel() {

    var context = AppContext.getInstance();

    val dataSource = DependencyProvider.getInstance().getUserSource();
    val profileState = context.getProfileState()


    var usersList = mutableStateListOf<ProfileModel>()
    var readyForSearch = mutableStateOf(true)

    var isLoading = mutableStateOf(true)

    var filterPopUp = mutableStateOf(false)


    fun initSearch(userid: String){
        readyForSearch.value = false;
        isLoading.value = true
        dataSource.getFollowing(userid,profileState.value.token) {
            if (usersList!=null) {
                it.getResult()?.let { profileModels ->
                    usersList.clear()
                    profileModels.forEach { profile ->
                        print(profile)
                        dataSource.userById(profile, profileState.value.token) { it ->
                            println(it.getResult())
                            it.getResult()?.let { it1 -> usersList.add(it1)
                                println(usersList[0])}

                        }
                    }

                }
            }
            readyForSearch.value = true;
            isLoading.value = false
        }
    }

    fun search(q : String){
        if(readyForSearch.value){
            readyForSearch.value = false;
            isLoading.value = true
            dataSource.userByUsername(q,profileState.value.token) {
                it.getResult()?.let {profileModel ->
                    usersList.clear()
                    usersList.addAll(profileModel)
                }
                readyForSearch.value = true;
                isLoading.value = false
            }
        }

    }

    fun getNav() : NavHostController{
        return context.getNav()
    }




}