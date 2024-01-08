package com.example.synema.controller

import WatchlistAPISource
import android.annotation.SuppressLint
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import com.example.synema.Data.Watchlists.WatchlistDataSource
import com.example.synema.Data.movies.MockMovieDataSource
import com.example.synema.Data.movies.MovieApiSource
import com.example.synema.Data.movies.MovieDataSource
import com.example.synema.Data.users.MockUserDataSource
import com.example.synema.Data.users.UserAPISource
import com.example.synema.Data.users.UserDataSource
import com.example.synema.model.ProfileModel

class AppContext private constructor() {
    companion object {

        @Volatile
        private var instance: AppContext? = null


        var profileState = mutableStateOf(ProfileModel("","","",""))

        @SuppressLint("StaticFieldLeak")
        lateinit var navHostController :  NavHostController

        fun getInstance() =
            instance ?: synchronized(this) {
                instance ?: AppContext().also { instance = it }
            }
        fun setNav(navHostController: NavHostController){
            this.navHostController = navHostController;
        }

    }
    fun getNav(): NavHostController {
        return navHostController;
    }

    fun setPofileState(newState : ProfileModel){
        profileState.value = newState
    }

    fun getProfileState() : MutableState<ProfileModel> {
        return profileState;
    }





}

