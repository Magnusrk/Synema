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

class SignupViewModel : ViewModel() {


    private var username  =  mutableStateOf("")
    private var email  =  mutableStateOf("")
    private var password  =  mutableStateOf("")
    var error  =  mutableStateOf("")

    var movieBanners =  mutableStateListOf<MovieModel>()



    fun editUsername(it : String) {username.value = it}
    fun editEmail(it : String) {email.value = it}
    fun editPassword(it : String) {password.value = it}



     fun login() {
        AppContext.getNav().navigate("login")
    }

    fun signup() {
        val source = DependencyProvider.getInstance().getUserSource();
        source.signupUser(username.value, email.value, password.value) {
            if (it.successful()) {
                AppContext.profileState.value =it.getResult()?.profile!!;
                AppContext.getNav().navigate("home")
            } else{
                error.value = (it.getStatus())
            }
        };
    }

}