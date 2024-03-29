package com.example.synema.viewmodel.Landing

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.synema.Data.DataStore.DataStoreManager
import com.example.synema.Data.DependencyProvider
import com.example.synema.controller.AppContext
import com.example.synema.model.MovieModel
import kotlinx.coroutines.launch

class SignupViewModel : ViewModel() {


    private var username  =  mutableStateOf("")
    private var email  =  mutableStateOf("")
    private var password  =  mutableStateOf("")
    var error  =  mutableStateOf("")

    var movieBanners =  mutableStateListOf<MovieModel>()

    val context = AppContext.getInstance();



    fun editUsername(it : String) {username.value = it}
    fun editEmail(it : String) {email.value = it}
    fun editPassword(it : String) {password.value = it}



     fun login() {
        context.getNav().navigate("login")
    }

    fun signup(c : Context) {
        val source = DependencyProvider.getInstance().getUserSource();
        source.signupUser(username.value, email.value, password.value) {
            if (it.successful()) {
                context.setProfileState(it.getResult()?.profile!!);
                loginAfterSignUp(c, email.value, password.value)

            } else{
                error.value = (it.getStatus())
            }
        };
    }

    fun loginAfterSignUp(c: Context, email : String, password : String) {
        val source = DependencyProvider.getInstance().getUserSource();
        source.LoginUser(email, password, callback = {
            if(it.successful()){
                context.setProfileState(it.getResult()?.profile!!);
                viewModelScope.launch {
                    DataStoreManager(c).saveToDataStore(context.getProfileState().value);
                }.let {
                    context.getNav().navigate("home")
                }

            } else{
                error.value = (it.getStatus())
            }
        });
    }

}