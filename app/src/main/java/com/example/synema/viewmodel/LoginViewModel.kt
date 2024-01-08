package com.example.synema.viewmodel

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.synema.Data.DependencyProvider
import com.example.synema.controller.AppContext
import com.example.synema.model.MovieModel
import com.example.synema.model.ProfileModel

class LoginViewModel : ViewModel() {


    private var email  =  mutableStateOf("")
    private var password  =  mutableStateOf("")
    var error  =  mutableStateOf("")

    var context = AppContext.getInstance();

    var movieBanners =  mutableStateListOf<MovieModel>()

    fun editEmail(it : String) {email.value = it}
    fun editPassword(it : String) {password.value = it}



     fun login() {
        val source = DependencyProvider.getInstance().getUserSource();
        source.LoginUser(email.value, password.value, callback = {
            if(it.successful()){
                context.setPofileState(it.getResult()?.profile!!);
                context.getNav().navigate("home")
            } else{
                error.value = (it.getStatus())
            }
        });
    }

    fun signup() {
        context.getNav().navigate("signup")
    }

     fun getMoviePosters(){
        val dataSource = DependencyProvider.getInstance().getMovieSource();
        dataSource.loadDiscoverMovies (){
            it.getResult()?.let {movieModel ->
                movieBanners.clear()
                movieBanners.addAll(movieModel)
            }
        }
    }

}