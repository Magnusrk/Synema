package com.example.synema.controller

import WatchlistAPISource
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

        lateinit var navHostController :  NavHostController
        fun setNav(navHostController: NavHostController){
            this.navHostController = navHostController;
        }
        fun getInstance() =
            instance ?: synchronized(this) {
                instance ?: AppContext().also { instance = it }
            }


        fun getNav(): NavHostController {
            return this.navHostController;
        }
    }

    fun getProfileState() : MutableState<ProfileModel> {
        return profileState;
    }



}

