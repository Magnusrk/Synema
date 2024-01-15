package com.example.synema.viewmodel.media

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import com.example.synema.Data.DependencyProvider
import com.example.synema.controller.AppContext
import com.example.synema.model.ActorModel
import com.example.synema.model.CreditsModel
import com.example.synema.model.MovieModel
import com.example.synema.model.ReviewModel

class ActorDetailsViewModel : ViewModel() {


    val actorID  = mutableStateOf<String>("");

    val isLoading = mutableStateOf(true);

    val source = DependencyProvider.getInstance().getMovieSource();


    var moviesStarring = mutableStateListOf<MovieModel>()


    private val nav = AppContext.getInstance().getNav()


    var actor = mutableStateOf(ActorModel())

    fun init(actorID : String){
        this.actorID.value = actorID
        loadActor()

    }

    fun loadActor(){
        isLoading.value = true
        source.getActorDetails(actorID.value) {
            if(it.getResult() != null){
                actor.value = it.getResult()!!
                source.starringMovies(actor.value.id.toString()){ movieList ->
                    if(movieList.getResult() != null){
                        moviesStarring.clear()
                        moviesStarring.addAll(movieList.getResult()!!);
                    }
                    this.isLoading.value = false;
                }
            }
            isLoading.value = false
        }

    }



    fun back(){
        nav.popBackStack()
    }

    fun getNav() : NavHostController {
        return nav
    }



}