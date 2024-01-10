package com.example.synema.viewmodel.Watchlists

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import com.example.synema.Data.DependencyProvider
import com.example.synema.controller.AppContext
import com.example.synema.model.WatchlistModel

class WatchlistViewModel : ViewModel() {
    var context = AppContext.getInstance();

    val isLoading = mutableStateOf(true);

    val dataSource = DependencyProvider.getInstance().getWatchlistSource();

    var watchlistList = mutableStateListOf<WatchlistModel>()

    val popupControl = mutableStateOf(false)
    val newWatchlistName =  mutableStateOf("")

    private val profileState = context.getProfileState()

    fun loadWatchlists(){
        isLoading.value = true;
        dataSource.read_db(profileState.value.token){
            if (it.successful()) {
                it.getResult()?.let {watchlistModel ->
                    watchlistList.clear()
                    watchlistList.addAll(watchlistModel)
                }
            }
            isLoading.value = false;

        }
    }

    fun createWatchlist(){
        isLoading.value = true
        popupControl.value = false
        dataSource.createWatchlist(newWatchlistName.value, profileState.value.token){
            context.getNav().currentDestination?.let { context.getNav().navigate(it.id) }
        }


    }

    fun getNav() : NavHostController{
        return context.getNav()
    }


}

