package com.example.synema.Data

import WatchlistAPISource
import com.example.synema.Data.Watchlists.WatchlistDataSource
import com.example.synema.Data.movies.MockMovieDataSource
import com.example.synema.Data.movies.MovieApiSource
import com.example.synema.Data.movies.MovieDataSource
import com.example.synema.Data.users.MockUserDataSource
import com.example.synema.Data.users.UserAPISource
import com.example.synema.Data.users.UserDataSource
class DependencyProvider private constructor() {
    companion object {

        @Volatile
        private var instance: DependencyProvider? = null


        private lateinit var movieSource : MovieDataSource;

        private lateinit var userSource : UserDataSource;

        private lateinit var watchlistSource : WatchlistAPISource;



        fun getInstance() =
            instance ?: synchronized(this) {
                instance ?: DependencyProvider().also { instance = it }
            }
    }

    fun create(debug : Boolean){
        if(debug){
            movieSource = MockMovieDataSource();
            userSource = MockUserDataSource();
        } else{

            //movieSource = MovieApiSource() For now
            movieSource = MovieApiSource()
            userSource = UserAPISource()
            watchlistSource =WatchlistAPISource()
        }
    }

    fun getUserSource() : UserDataSource {
        return userSource;
    }

    fun getMovieSource() : MovieDataSource{
        return movieSource;
    }
    fun getWatchlistSource(): WatchlistDataSource{
        return watchlistSource;
    }
}

