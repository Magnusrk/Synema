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
import com.example.synema.model.CreditsModel
import com.example.synema.model.MovieModel
import com.example.synema.model.ReviewModel

class MediaDetailsViewModel : ViewModel() {


    val movieID  = mutableStateOf<String>("");

    val isLoading = mutableStateOf(true);

    val source = DependencyProvider.getInstance().getMovieSource();

    var reviewList = mutableStateListOf<ReviewModel>()

    var similarMovies = mutableStateListOf<MovieModel>()

    var actorList = mutableStateListOf<CreditsModel>()

    val profileState = AppContext.getInstance().getProfileState().value;

    private val nav = AppContext.getInstance().getNav()


    var movie = mutableStateOf(
            MovieModel(
                0,
                "",
                "",
                "Loading...",
                "Loading...",
                0,
                "",
                ""
            )
        )

    fun init(movieID : String){
        this.movieID.value = movieID
        this.isLoading.value = true;
        loadMoviesAndSimilar()
        loadReviews()
        loadCredits()
    }

    fun loadMoviesAndSimilar(){
        source.loadMovie(movieID.value) {
            movie.value = it.getResult()!!
            source.similarMovies(movie.value.id.toString()){
                if(it.getResult() != null){
                    similarMovies.clear()
                    similarMovies.addAll(it.getResult()!!);
                }
                this.isLoading.value = false;
            }
        }

    }

    fun loadCredits(){
        source.loadCredits(movieID.value, profileState.token){
            if (it.successful()) {
                actorList.clear()
                actorList.addAll(it.getResult()!!)
            }
        }
    }




    fun loadReviews(){
        source.getReviewsForMovie(movieID.toString(), profileState.token) {
            if (it.successful()) {
                it.getResult()?.let { reviewModel ->
                    reviewList.clear()
                    reviewList.addAll(reviewModel)
                }
            }
        }
    }

    fun saveMovie(){
        nav.navigate("mediaDetails/" + movie.value.id + "/save")
    }

    fun reviewMovie(){
        nav.navigate("mediaDetails/" + movie.value.id + "/review")
    }

    fun back(){
        nav.popBackStack()
    }

    fun getNav() : NavHostController {
        return nav
    }



}