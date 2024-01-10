package com.example.synema.Data.movies

import com.example.synema.R
import com.example.synema.model.ApiResponse
import com.example.synema.model.MovieModel
import com.example.synema.model.ProfileModel
import com.example.synema.model.ReviewModel
import com.example.synema.model.UserModel


class MockMovieDataSource : MovieDataSource {

    override fun loadMovie(id: String, callback: (ApiResponse<MovieModel>) -> Unit){
        callback(ApiResponse(MovieModel(
            507089,
            "https://www.themoviedb.org/t/p/w300_and_h450_bestv2/A4j8S6moJS2zNtRR8oWF08gRnL5.jpg",
            "https://www.themoviedb.org/t/p/w300_and_h450_bestv2/A4j8S6moJS2zNtRR8oWF08gRnL5.jpg",
            "Five Nights at Freddy's",
            "Recently fired and desperate for work, a troubled young man named Mike agrees to take a position as a night security guard at an abandoned theme restaurant: Freddy Fazbear's Pizzeria. But he soon discovers that nothing at Freddy's is what it seems.",
            8.4/2,
            "2023-10-25"
        )))
    }

    override fun loadMovies(): List<MovieModel>{
        return listOf<MovieModel>(
            MovieModel(
                507089,
                "https://www.themoviedb.org/t/p/w300_and_h450_bestv2/A4j8S6moJS2zNtRR8oWF08gRnL5.jpg",
                "https://www.themoviedb.org/t/p/w300_and_h450_bestv2/A4j8S6moJS2zNtRR8oWF08gRnL5.jpg",
                "Five Nights at Freddy's",
                "Recently fired and desperate for work, a troubled young man named Mike agrees to take a position as a night security guard at an abandoned theme restaurant: Freddy Fazbear's Pizzeria. But he soon discovers that nothing at Freddy's is what it seems.",
                8.4/2,
                "2023-10-25"
            ),
            MovieModel(
                951491,
                "https://www.themoviedb.org/t/p/w300_and_h450_bestv2/aQPeznSu7XDTrrdCtT5eLiu52Yu.jpg",
                "https://www.themoviedb.org/t/p/w300_and_h450_bestv2/aQPeznSu7XDTrrdCtT5eLiu52Yu.jpg",
                "Saw X",
                "Between the events of 'Saw' and 'Saw II', a sick and desperate John Kramer travels to Mexico for a risky and experimental medical procedure in hopes of a miracle cure for his cancer, only to discover the entire operation is a scam to defraud the most vulnerable. Armed with a newfound purpose, the infamous serial killer returns to his work, turning the tables on the con artists in his signature visceral way through devious, deranged, and ingenious traps.",
                7.4/2,
                "2023-09-26"
            ),
            MovieModel(
                939335,
                "https://www.themoviedb.org/t/p/w300_and_h450_bestv2/qXChf7MFL36BgoLkiB3BzXiwW82.jpg",
                "https://www.themoviedb.org/t/p/w300_and_h450_bestv2/qXChf7MFL36BgoLkiB3BzXiwW82.jpg",
                "Muzzle",
                "LAPD K-9 officer Jake Rosser has just witnessed the shocking murder of his dedicated partner by a mysterious assailant. As he investigates the shooter’s identity, he uncovers a vast conspiracy that has a chokehold on the city in this thrilling journey through the tangled streets of Los Angeles and the corrupt bureaucracy of the LAPD.",
                6.3/2,
                "2023-09-29"
            ),
            MovieModel(
                807172,
                "https://www.themoviedb.org/t/p/w300_and_h450_bestv2/qVKirUdmoex8SdfUk8WDDWwrcCh.jpg",
                "https://www.themoviedb.org/t/p/w300_and_h450_bestv2/qVKirUdmoex8SdfUk8WDDWwrcCh.jpg",
                "The Exorcist: Believer",
                "When his daughter and her friend show signs of demonic possession, it unleashes a chain of events that forces single father, Victor Fielding, to confront the nadir of evil. Terrified and desperate, he seeks out the only person alive who's witnessed anything like it before.",
                6.1/2,
                "2023-10-04"
            )
        )
    }

 /* override fun loadReviews() : List <ReviewModel>{
        return listOf(
            ReviewModel(
                ProfileModel(
                    "test",
                    "Chuck Norris",
                    "",
                    "bio"),
                """Amazing, Nolan is finally back and he's so hot!
                    |With all the rampant think pieces questioning the probability of every science fiction film that comes out, it's comforting to across a movie that doesn't really claim to have any of the answers.
                """.trimMargin(),
                5,
                MovieModel(
                    507089,
                    "https://www.themoviedb.org/t/p/w300_and_h450_bestv2/A4j8S6moJS2zNtRR8oWF08gRnL5.jpg",
                    "https://www.themoviedb.org/t/p/w300_and_h450_bestv2/A4j8S6moJS2zNtRR8oWF08gRnL5.jpg",
                    "Five Nights at Freddy's",
                    "Recently fired and desperate for work, a troubled young man named Mike agrees to take a position as a night security guard at an abandoned theme restaurant: Freddy Fazbear's Pizzeria. But he soon discovers that nothing at Freddy's is what it seems.",
                    8.4/2,
                    "2023-10-25"
                )
            ),
            ReviewModel(
                ProfileModel(
                    "test2",
                    "Steve Jobs",
                    "",
                    "bio"),
                "Mid",
                3,
                MovieModel(
                    507089,
                    "https://www.themoviedb.org/t/p/w300_and_h450_bestv2/A4j8S6moJS2zNtRR8oWF08gRnL5.jpg",
                    "https://www.themoviedb.org/t/p/w300_and_h450_bestv2/A4j8S6moJS2zNtRR8oWF08gRnL5.jpg",
                    "Five Nights at Freddy's",
                    "Recently fired and desperate for work, a troubled young man named Mike agrees to take a position as a night security guard at an abandoned theme restaurant: Freddy Fazbear's Pizzeria. But he soon discovers that nothing at Freddy's is what it seems.",
                    8.4/2,
                    "2023-10-25"
                )

            ),
            ReviewModel(
                ProfileModel(
                    "test3",
                    "Carl Sagan",
                    "",
                    "bio"),
                "I liked the black hole part!",
                4,
                MovieModel(
                    507089,
                    "https://www.themoviedb.org/t/p/w300_and_h450_bestv2/A4j8S6moJS2zNtRR8oWF08gRnL5.jpg",
                    "https://www.themoviedb.org/t/p/w300_and_h450_bestv2/A4j8S6moJS2zNtRR8oWF08gRnL5.jpg",
                    "Five Nights at Freddy's",
                    "Recently fired and desperate for work, a troubled young man named Mike agrees to take a position as a night security guard at an abandoned theme restaurant: Freddy Fazbear's Pizzeria. But he soon discovers that nothing at Freddy's is what it seems.",
                    8.4/2,
                    "2023-10-25"
                )
            )
        )

    }


  */


    override fun loadDiscoverMovies(genres : String, callback: (ApiResponse<List<MovieModel>>) -> Unit) {
        callback(ApiResponse(
            loadMovies()
        ))
    }

    override fun loadNewMovies(callback: (ApiResponse<List<MovieModel>>) -> Unit) {
        callback(ApiResponse(
            loadMovies()
        ))
    }

    override fun searchMovies(genres : String, callback: (ApiResponse<List<MovieModel>>) -> Unit) {
        callback(ApiResponse(
            loadMovies()
        ))
    }

    override fun createReviewForMovie(
        movieId: String,
        review: String,
        rating: Int,
        token: String,
        profileModel: ProfileModel,
        callback: (ApiResponse<String>) -> Unit
    ) {
        TODO("Not yet implemented")
    }

    override fun delete_review(
        movieId: String,
        token: String,
        profileModel: ProfileModel,
        callback: (ApiResponse<String>) -> Unit
    ) {
        TODO("Not yet implemented")
    }

    override fun getReviewsForMovie(
        movieId: String,
        token: String,
        callback: (ApiResponse<List<ReviewModel>>) -> Unit
    ) {
        TODO("Not yet implemented")
    }

}