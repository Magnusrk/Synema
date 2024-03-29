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
    val deleteConfirmationPopupControl = mutableStateOf(false)
    val newWatchlistName =  mutableStateOf("")
    val watchlistToDelete =  mutableStateOf("")

    private val profileState = context.getProfileState()

    fun loadWatchlists(){
        println(profileState.value.token)
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

    fun loadOtherUserList(userId: String){
        println(profileState.value.token)
        isLoading.value = true;
        dataSource.read_otherUsers_db(userId, profileState.value.token){
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
        println("token=" + profileState.value.token)
        println("NewWL name = " + newWatchlistName.value)
        dataSource.createWatchlist(newWatchlistName.value, profileState.value.token){
            context.getNav().currentDestination?.let { context.getNav().navigate(it.id) }
        }


    }

    fun getNav() : NavHostController{
        return context.getNav()
    }

    fun DeleteWatchlist(){
        val dataSource = DependencyProvider.getInstance().getWatchlistSource()
        dataSource.deleteWatchlist(watchlistToDelete.value, context.getProfileState().value.token) { response ->
            // Close the dialog and navigate to watchlist
            deleteConfirmationPopupControl.value = false
            context.getNav().navigate("watchlists")
        }
    }

    fun promptDeletion(id : String){
        watchlistToDelete.value = id
        deleteConfirmationPopupControl.value=true
    }


}

