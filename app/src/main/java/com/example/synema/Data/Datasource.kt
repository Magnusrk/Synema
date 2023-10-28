package com.example.synema.Data

import com.example.synema.R
import com.example.synema.model.MovieModel
import com.example.synema.model.ProfileModel
import com.example.synema.model.ReviewModel


class Datasource() {

    fun loadMovie(): MovieModel{
        return MovieModel(R.string.movie1, R.drawable.image1, 1, 4.5f)

    }

    fun loadMovies(): List<MovieModel>{
        return listOf<MovieModel>(
            MovieModel(R.string.movie1, R.drawable.image1, 1, 4.5f),
            MovieModel(R.string.movie2, R.drawable.image2, 2, 3.2f),
            MovieModel(R.string.movie2, R.drawable.image2 , 2, 3.2f),
            MovieModel(R.string.movie2, R.drawable.image2, 2, 3.2f)
        )
    }

    fun loadReviews() : List <ReviewModel>{
        return listOf(
            ReviewModel(
                ProfileModel(
                    "test",
                    "Chuck Norris",
                    ""
                    ),
                """Amazing, Nolan is finally back and he's so hot!
                    |With all the rampant think pieces questioning the probability of every science fiction film that comes out, it's comforting to across a movie that doesn't really claim to have any of the answers.
                """.trimMargin(),
                5,
                MovieModel(R.string.movie1, R.drawable.image1, 1, 4.5f)
                ),
            ReviewModel(
                ProfileModel(
                    "test2",
                    "Steve Jobs",
                    ""
                ),
                "Mid",
                3,
                MovieModel(R.string.movie1, R.drawable.image1, 1, 4.5f)

            ),
            ReviewModel(
                ProfileModel(
                    "test3",
                    "Carl Sagan",
                    ""
                ),
                "I liked the black hole part!",
                4,
                MovieModel(R.string.movie1, R.drawable.image1, 1, 4.5f)
            )
        )

    }
}
