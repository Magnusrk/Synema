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

class SearchViewModel : ViewModel() {

    var context = AppContext.getInstance();

    val dataSource = DependencyProvider.getInstance().getMovieSource();


    var movieList = mutableStateListOf<MovieModel>()
    var readyForSearch = mutableStateOf(true)

    var isLoading = mutableStateOf(true)

    var filterPopUp = mutableStateOf(false)


    fun initSearch(){
        readyForSearch.value = false;
        isLoading.value = true
        dataSource.loadDiscoverMovies() {

            it.getResult()?.let {movieModels ->
                movieList.clear()
                movieList.addAll(movieModels);
                readyForSearch.value = true;
                isLoading.value = false
            }
        }
    }

    fun search(q : String){
        if(readyForSearch.value){
            readyForSearch.value = false;
            isLoading.value = true
            dataSource.searchMovies(q) {
                it.getResult()?.let {movieModel ->
                    movieList.clear()
                    movieList.addAll(movieModel)
                    readyForSearch.value = true;
                    isLoading.value = false
                }
            }
        }

    }

    fun getNav() : NavHostController{
        return context.getNav()
    }




}