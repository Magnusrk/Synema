package com.example.synema.Data

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
        }
    }

    fun getUserSource() : UserDataSource {
        return userSource;
    }

    fun getMovieSource() : MovieDataSource{
        return movieSource;
    }
}

