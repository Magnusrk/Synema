package com.example.synema.viewmodel.media

import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import com.example.synema.Data.DependencyProvider
import com.example.synema.controller.AppContext
import com.example.synema.model.GenreModel
import com.example.synema.model.MovieModel

class HomeViewModel : ViewModel() {
    var context = AppContext.getInstance();

    val isLoading = mutableStateOf(true);
    val numOfCategories = 6;
    val numLoaded = mutableStateOf(0);


    val minRating = mutableFloatStateOf(0.1f);




    var newList = mutableStateListOf<MovieModel>()

    var discoverList = mutableStateListOf<MovieModel>()

    var comedyList = mutableStateListOf<MovieModel>()

    var horrorList = mutableStateListOf<MovieModel>()

    var animeList = mutableStateListOf<MovieModel>()

    var historyList = mutableStateListOf<MovieModel>()

    var genreExpanded = mutableStateOf(false)
    var filterGenres = mutableStateListOf<GenreModel>()

    var hasFiltered = mutableStateOf(false)
    var filterLoad = mutableStateOf(false)

    val dataSource = DependencyProvider.getInstance().getMovieSource();

    var filteredList = mutableStateListOf<MovieModel>()


    var genres = mutableStateListOf<GenreModel>(
    GenreModel("28",
        "Action"
    ),
   GenreModel( "12",
    "Adventure"
    ),
   GenreModel(
    "16",
    "Animation"
    ),
   GenreModel (
    "35",
    "Comedy"
    ),
    GenreModel(
    "80",
    "Crime"
    ),
    GenreModel(
    "99",
    "Documentary"
    ),
   GenreModel (
    "18",
    "Drama"
    ),
   GenreModel (
    "10751",
    "Family"
    ),
    GenreModel(
    "14",
    "Fantasy"
    ),
    GenreModel(
    "36",
    "History"
    ),
    GenreModel(
    "27",
    "Horror"
    ),
    GenreModel(
    "10402",
    "Music"
    ),
    GenreModel(
    "9648",
    "Mystery"
    ),
    GenreModel(
    "10749",
    "Romance"
    ),
    GenreModel(
    "878",
    "Science Fiction"
    ),
    GenreModel(
    "10770",
    "TV Movie"
    ),
   GenreModel (
    "53",
    "Thriller"
    ),
    GenreModel(
    "10752",
    "War"
    ),
    GenreModel(
    "37",
    "Western"
    )
    )


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

    fun genreIsSelected(genreModel : GenreModel) : Boolean{
        return filterGenres.contains(genreModel)
    }

    fun toggleGenre(genre : GenreModel){
        if(genreIsSelected(genre)){
            filterGenres.remove(genre)
        }else{
            filterGenres.add(genre)

        }
    }

    fun filterSearch(){
        hasFiltered.value = true
        filterLoad.value = true
        var genreString = ""
        filterGenres.forEach{
            genre -> genreString += genre.id +","
        }
        dataSource.loadFilterMovies (genreString, minRating.value){
            if(it.successful()){
                filteredList.clear()
                it.getResult()?.let { it1 -> filteredList.addAll(it1) }

            }
            filterLoad.value = false
        }
    }





    fun getNav() : NavHostController{
        return context.getNav()
    }

}