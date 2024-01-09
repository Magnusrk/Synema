package com.example.synema.viewmodel

import android.content.Context
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.synema.Data.DataStore.DataStoreManager
import com.example.synema.Data.DependencyProvider
import com.example.synema.controller.AppContext
import com.example.synema.model.MovieModel
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {


    private var email  =  mutableStateOf("")
    private var password  =  mutableStateOf("")
    var error  =  mutableStateOf("")

    var context = AppContext.getInstance();

    var movieBanners =  mutableStateListOf<MovieModel>()

    fun editEmail(it : String) {email.value = it}
    fun editPassword(it : String) {password.value = it}



     fun login(c: Context) {
        val source = DependencyProvider.getInstance().getUserSource();
        source.LoginUser(email.value, password.value, callback = {
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

    fun checkUserLoggedIn(c : Context){
        viewModelScope.launch {
            DataStoreManager(c).getFromDataStore().collect{
                profile -> if(profile.token == ""){
                    return@collect
                }

                val dataSource = DependencyProvider.getInstance().getUserSource()

                dataSource.verifyToken(profile.token){
                    if(!it.successful()){
                        return@verifyToken
                    }
                    if(it.getResult() == false){
                        return@verifyToken
                    }
                    context.setProfileState(profile)
                    context.getNav().navigate("home")
                }

            }
        }
    }



}