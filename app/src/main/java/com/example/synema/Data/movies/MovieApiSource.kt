package com.example.synema.Data.movies
import android.util.Log
import com.example.synema.R
import com.example.synema.controller.MovieAPI
import com.example.synema.controller.UserAPI
import com.example.synema.model.ApiResponse
import com.example.synema.model.MovieModel
import com.example.synema.model.ProfileModel
import com.example.synema.model.ReviewModel
import com.example.synema.model.UserModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MovieApiSource : MovieDataSource {

    //private val BASE_URL = "localhost:8000"

    private val BASE_URL = "https://cwjtedqahp.eu10.qoddiapp.com/"

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    override fun loadMovie(id: String, callback: (ApiResponse<MovieModel>) -> Unit){

        val api = retrofit.create(MovieAPI::class.java)
        val call: Call<MovieModel> = api.getMovieById(id);

        call.enqueue(object: Callback<MovieModel> {
            override fun onResponse(
                call: Call<MovieModel>,
                response: Response<MovieModel>
            ) {
                if(response.isSuccessful) {
                    callback(ApiResponse(response.body()!!))
                }else{
                    callback(ApiResponse(null, true, "Couldn't find movie"))
                }
            }

            override fun onFailure(call: Call<MovieModel>, t: Throwable) {
                callback(ApiResponse(null, true, t.message!!));
            }

        })
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

    override fun loadReviews() : List <ReviewModel>{
        return listOf(
            ReviewModel(
                ProfileModel(
                    "test",
                    "Chuck Norris",
                    "",
                    "bio"
                ),
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
                    "bio"
                ),
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
                    "bio"
                ),
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

    override fun loadDiscoverMovies(genres : String, callback: (ApiResponse<List<MovieModel>>) -> Unit) {

        val api = retrofit.create(MovieAPI::class.java)
        val call: Call<List<MovieModel>> = api.discoverMovies(genres);

        call.enqueue(object: Callback<List<MovieModel>> {
            override fun onResponse(
                call: Call<List<MovieModel>>,
                response: Response<List<MovieModel>>
            ) {
                if(response.isSuccessful) {
                    Log.d("Main", "success!" + response.body().toString())
                    callback(ApiResponse(response.body()!!))
                }else{
                    callback(ApiResponse(null, true, "Couldn't load movies"))
                }
            }

            override fun onFailure(call: Call<List<MovieModel>>, t: Throwable) {
                callback(ApiResponse(null, true, t.message!!));
            }

        })

    }

}