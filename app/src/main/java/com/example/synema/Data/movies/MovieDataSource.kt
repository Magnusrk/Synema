package com.example.synema.Data.movies

import com.example.synema.model.MovieModel
import com.example.synema.model.ReviewModel
import com.example.synema.model.UserModel


interface MovieDataSource {

    fun loadMovie(id : String): MovieModel;
    fun loadMovies() : List<MovieModel>;
    fun loadReviews() : List<ReviewModel>;


}
