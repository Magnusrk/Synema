package com.example.synema.Data.movies

import com.example.synema.R
import com.example.synema.model.MovieModel
import com.example.synema.model.ProfileModel
import com.example.synema.model.ReviewModel
import com.example.synema.model.UserModel
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MovieApiSource : MovieDataSource {

    private val BASE_URL = "http://10.209.225.17:8000";

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    override fun loadMovie(id: String): MovieModel{
        TODO()

    }

    override fun loadMovies(): List<MovieModel>{
        TODO()
    }

    override fun loadReviews() : List <ReviewModel>{
        TODO()
    }

}
