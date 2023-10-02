package com.example.synema.Data

import com.example.synema.R
import com.example.synema.model.Movie


class Datasource() {
    fun loadMovies(): List<Movie>{
        return listOf<Movie>(
            Movie(R.string.movie1, R.drawable.image1),
            Movie(R.string.movie2, R.drawable.image2),
            Movie(R.string.movie2, R.drawable.image2),
            Movie(R.string.movie2, R.drawable.image2)
        )
    }
}
