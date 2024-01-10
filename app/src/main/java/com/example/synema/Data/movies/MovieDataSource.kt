package com.example.synema.Data.movies

import com.example.synema.model.ApiResponse
import com.example.synema.model.MovieModel
import com.example.synema.model.ProfileModel
import com.example.synema.model.ReviewModel
import com.example.synema.model.UserModel


interface MovieDataSource {

    fun loadMovie(id: String, callback: (ApiResponse<MovieModel>) -> Unit);
    fun loadMovies() : List<MovieModel>;
    /*
    fun loadReviews() : List<ReviewModel>;
*/
    fun loadDiscoverMovies(genres : String = "", callback : (ApiResponse<List<MovieModel>>) -> Unit);

    fun loadNewMovies(callback : (ApiResponse<List<MovieModel>>) -> Unit);
    fun searchMovies(query : String, callback : (ApiResponse<List<MovieModel>>) -> Unit);

    fun getReviewsForMovie(movieId: String,token: String, callback: (ApiResponse<List<ReviewModel>>) -> Unit)
    fun createReviewForMovie(movieId: String, review: String,rating: Int, token: String, profileModel: ProfileModel, callback: (ApiResponse<String>) -> Unit)
    fun delete_review(movieId: String, token: String, profileModel: ProfileModel, callback: (ApiResponse<String>) -> Unit)
}