package com.example.synema.Data

import com.example.synema.R
import com.example.synema.model.MovieModel


class Datasource() {
    fun loadMovies(): List<MovieModel>{
        return listOf<MovieModel>(
            MovieModel(R.string.movie1, R.drawable.image1),
            MovieModel(R.string.movie2, R.drawable.image2),
            MovieModel(R.string.movie2, R.drawable.image2),
            MovieModel(R.string.movie2, R.drawable.image2)
        )
    }
}
