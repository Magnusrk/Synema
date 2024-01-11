package com.example.synema.viewmodel.Watchlists

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import com.example.synema.Data.DependencyProvider
import com.example.synema.controller.AppContext
import com.example.synema.model.MovieModel
import okhttp3.internal.wait

class MyListViewModel : ViewModel() {
    var context = AppContext.getInstance();

    val isLoading = mutableStateOf(true);

    val movieSource = DependencyProvider.getInstance().getMovieSource();
    val watchSource = DependencyProvider.getInstance().getWatchlistSource();

    val movielist = mutableStateListOf<MovieModel>()

    val profileState = context.getProfileState()

    val loadedMovies = mutableStateOf(0)
    val moviesToLoad = mutableStateOf(0)



    fun getWatchlist(watchlistID : String){
        isLoading.value = true
        loadedMovies.value = 0
        watchSource.getWatchlistById(watchlistID.toString(), token = profileState.value.token) {
            if (it.successful()) {

                it.getResult()?.let { watchlistModel ->
                    movielist.clear()
                    moviesToLoad.value = watchlistModel.movieIds.size
                    if(moviesToLoad.value == 0){
                        isLoading.value = false;
                        return@getWatchlistById
                    }
                    watchlistModel.movieIds.forEach { movie : String ->
                        movieSource.loadMovie(movie) {
                            movielist.add(it.getResult()!!)
                            updateLoading()
                        }
                    }


                }


            }
        }
    }

    private fun updateLoading(){
        loadedMovies.value += 1
        if(loadedMovies.value >= moviesToLoad.value){
            isLoading.value = false

        }
    }

    fun clickMovie(movieId: Int){
        context.getNav().navigate("mediaDetails/$movieId")
    }

    fun deleteMovieFromWatchlist(watchlistID: String, movieId: Int){
        watchSource.deleteMovieFromWatchlist(
            watchlistID,
            movieId.toString(),
            profileState.value.token
        ) {}
    }



    fun getNav() : NavHostController{
        return context.getNav()
    }


}

