package com.example.synema.viewmodel.media

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import com.example.synema.Data.DependencyProvider
import com.example.synema.controller.AppContext
import com.example.synema.model.MovieModel

class HomeViewModel : ViewModel() {
    var context = AppContext.getInstance();

    val isLoading = mutableStateOf(true);
    val numOfCategories = 6;
    val numLoaded = mutableStateOf(0);



    var newList = mutableStateListOf<MovieModel>()

    var discoverList = mutableStateListOf<MovieModel>()

    var comedyList = mutableStateListOf<MovieModel>()

    var horrorList = mutableStateListOf<MovieModel>()

    var animeList = mutableStateListOf<MovieModel>()

    var historyList = mutableStateListOf<MovieModel>()


    val dataSource = DependencyProvider.getInstance().getMovieSource();

    private fun loadNew(){

        dataSource.loadNewMovies (){
            it.getResult()?.let {movieModel ->
                newList.clear()
                newList.addAll(movieModel)
            }
            updateLoading()
        }
    }
    private fun loadDiscover(){
        dataSource.loadDiscoverMovies (){
            it.getResult()?.let {movieModel ->
                discoverList.clear()
                discoverList.addAll(movieModel)
            }
            updateLoading()
        }
    }
    private fun loadComedy(){
        dataSource.loadDiscoverMovies(genres = "35") {
            it.getResult()?.let {movieModel ->
                comedyList.clear()
                comedyList.addAll(movieModel)
            }
            updateLoading()

        }
    }
    private fun loadHorror(){
        dataSource.loadDiscoverMovies(genres = "27") {
            it.getResult()?.let {movieModel ->
                horrorList.clear()
                horrorList.addAll(movieModel)
            }
            updateLoading()

        }
    }
    private fun loadAnime(){
        dataSource.loadDiscoverMovies(genres = "16") {
            it.getResult()?.let {movieModel ->
                animeList.clear()
                animeList.addAll(movieModel)
            }
            updateLoading()
        }
    }
    private fun loadHistory(){
        dataSource.loadDiscoverMovies(genres = "36") {
            it.getResult()?.let {movieModel ->
                historyList.clear()
                historyList.addAll(movieModel)
            }
            updateLoading()
        }

    }

    private fun updateLoading(){
        numLoaded.value += 1;
        if(numLoaded.value >= numOfCategories){
            isLoading.value = false;
        }
    }



    fun loadMovies(){
        isLoading.value = true;
        numLoaded.value = 0;
        loadNew()
        loadDiscover()
        loadComedy()
        loadHorror()
        loadHistory()
        loadAnime()
    }



    fun getNav() : NavHostController{
        return context.getNav()
    }

}