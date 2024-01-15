package com.example.synema.Data.movies

import android.annotation.SuppressLint
import android.util.Log
import com.example.synema.controller.MovieAPI
import com.example.synema.model.ApiResponse
import com.example.synema.model.CreditsModel
import com.example.synema.model.ImagesModel
import com.example.synema.model.MovieModel
import com.example.synema.model.ProfileModel
import com.example.synema.model.ReviewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MovieApiSource : MovieDataSource {

    //private val BASE_URL = "http://192.168.0.107:8000"

    private val BASE_URL = "https://cwjtedqahp.eu10.qoddiapp.com/"

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    override fun loadMovie(id: String, callback: (ApiResponse<MovieModel>) -> Unit) {

        val api = retrofit.create(MovieAPI::class.java)
        val call: Call<MovieModel> = api.getMovieById(id);

        call.enqueue(object : Callback<MovieModel> {
            override fun onResponse(
                call: Call<MovieModel>,
                response: Response<MovieModel>
            ) {
                if (response.isSuccessful) {
                    callback(ApiResponse(response.body()!!))
                } else {
                    callback(ApiResponse(null, true, "Couldn't find movie"))
                }
            }

            override fun onFailure(call: Call<MovieModel>, t: Throwable) {
                callback(ApiResponse(null, true, t.message!!));
            }

        })
    }

    override fun loadCredits(id: String,token: String, callback: (ApiResponse<List<CreditsModel>>) -> Unit){
        val api = retrofit.create(MovieAPI::class.java)
        val call: Call<List<CreditsModel>> = api.getCreditsById(id)

        call.enqueue(object : Callback<List<CreditsModel>> {
            override fun onResponse(
                call: Call<List<CreditsModel>>,
                response: Response<List<CreditsModel>>
            ) {
                if (response.isSuccessful) {
                    Log.d("Main", "success!" + response.body().toString())
                    callback(ApiResponse(response.body()!!))
                } else {
                    callback(ApiResponse(null, true, "Couldn't load credits"))
                }
            }

            override fun onFailure(call: Call<List<CreditsModel>>, t: Throwable) {
                callback(ApiResponse(null, true, t.message!!));
            }

        })
    }

    override fun loadImages(id: String,token: String, callback: (ApiResponse<List<ImagesModel>>) -> Unit){
        val api = retrofit.create(MovieAPI::class.java)
        val call: Call<List<ImagesModel>> = api.getImagesById(id)

        call.enqueue(object : Callback<List<ImagesModel>> {
            override fun onResponse(
                call: Call<List<ImagesModel>>,
                response: Response<List<ImagesModel>>
            ) {
                if (response.isSuccessful) {
                    Log.d("Main", "success!" + response.body().toString())
                    callback(ApiResponse(response.body()!!))
                } else {
                    callback(ApiResponse(null, true, "Couldn't load credits"))
                }
            }

            override fun onFailure(call: Call<List<ImagesModel>>, t: Throwable) {
                callback(ApiResponse(null, true, t.message!!));
            }

        })
    }


    override fun loadMovies(): List<MovieModel> {
        return listOf<MovieModel>(
            MovieModel(
                507089,
                "https://www.themoviedb.org/t/p/w300_and_h450_bestv2/A4j8S6moJS2zNtRR8oWF08gRnL5.jpg",
                "https://www.themoviedb.org/t/p/w300_and_h450_bestv2/A4j8S6moJS2zNtRR8oWF08gRnL5.jpg",
                "Five Nights at Freddy's",
                "Recently fired and desperate for work, a troubled young man named Mike agrees to take a position as a night security guard at an abandoned theme restaurant: Freddy Fazbear's Pizzeria. But he soon discovers that nothing at Freddy's is what it seems.",
                8.4 / 2,
                "2023-10-25",
                "uhu uh uh"
            )
        )
    }
    /*

    override fun loadReviews(): List<ReviewModel> {
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
                    8.4 / 2,
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
                    8.4 / 2,
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
                    8.4 / 2,
                    "2023-10-25"
                )
            )
        )

    }

     */


    override fun loadDiscoverMovies(
        genres: String,
        callback: (ApiResponse<List<MovieModel>>) -> Unit
    ) {

        val api = retrofit.create(MovieAPI::class.java)
        val call: Call<List<MovieModel>> = api.discoverMovies(genres);

        call.enqueue(object : Callback<List<MovieModel>> {
            override fun onResponse(
                call: Call<List<MovieModel>>,
                response: Response<List<MovieModel>>
            ) {
                if (response.isSuccessful) {
                    Log.d("Main", "success!" + response.body().toString())
                    callback(ApiResponse(response.body()!!))
                } else {
                    callback(ApiResponse(null, true, "Couldn't load movies"))
                }
            }

            override fun onFailure(call: Call<List<MovieModel>>, t: Throwable) {
                callback(ApiResponse(null, true, t.message!!));
            }

        })

    }

    override fun loadNewMovies(callback: (ApiResponse<List<MovieModel>>) -> Unit) {

        val api = retrofit.create(MovieAPI::class.java)
        val call: Call<List<MovieModel>> = api.newMovies();

        call.enqueue(object : Callback<List<MovieModel>> {
            override fun onResponse(
                call: Call<List<MovieModel>>,
                response: Response<List<MovieModel>>
            ) {
                if (response.isSuccessful) {
                    Log.d("Main", "success!" + response.body().toString())
                    callback(ApiResponse(response.body()!!))
                } else {
                    callback(ApiResponse(null, true, "Couldn't load movies"))
                }
            }

            override fun onFailure(call: Call<List<MovieModel>>, t: Throwable) {
                callback(ApiResponse(null, true, t.message!!));
            }

        })

    }

    override fun searchMovies(query: String, callback: (ApiResponse<List<MovieModel>>) -> Unit) {

        val api = retrofit.create(MovieAPI::class.java)
        val call: Call<List<MovieModel>> = api.searchMovies(query);

        call.enqueue(object : Callback<List<MovieModel>> {
            override fun onResponse(
                call: Call<List<MovieModel>>,
                response: Response<List<MovieModel>>
            ) {
                if (response.isSuccessful) {
                    Log.d("Main", "success!" + response.body().toString())
                    callback(ApiResponse(response.body()!!))
                } else {
                    callback(ApiResponse(null, true, "Couldn't load movies"))
                }
            }

            override fun onFailure(call: Call<List<MovieModel>>, t: Throwable) {
                callback(ApiResponse(null, true, t.message!!));
            }

        })

    }

    override fun similarMovies(movieId: String, callback: (ApiResponse<List<MovieModel>>) -> Unit) {
        val api = retrofit.create(MovieAPI::class.java)
        val call: Call<List<MovieModel>> = api.similar(movieId);

        call.enqueue(object : Callback<List<MovieModel>> {
            override fun onResponse(
                call: Call<List<MovieModel>>,
                response: Response<List<MovieModel>>
            ) {
                if (response.isSuccessful) {
                    Log.d("Main", "success!" + response.body().toString())
                    callback(ApiResponse(response.body()!!))
                } else {
                    callback(ApiResponse(null, true, "Couldn't load movies"))
                }
            }

            override fun onFailure(call: Call<List<MovieModel>>, t: Throwable) {
                callback(ApiResponse(null, true, t.message!!));
            }

        })

    }

    override fun createReviewForMovie(
        movieId: String,
        review: String,
        rating: Int,
        token: String,
        profileModel: ProfileModel,
        callback: (ApiResponse<String>) -> Unit
    ) {
        val api = retrofit.create(MovieAPI::class.java)

        val createReviewCall: Call<String> = api.createReviewForMovie(
            movieId,
            ReviewModel(review, rating, movieId, profileModel.name, profileModel.id),
            token
        )

        createReviewCall.enqueue(object : Callback<String> {
            override fun onResponse(call: Call<String>, response: Response<String>) {
                if (response.isSuccessful) {
                    callback(
                        ApiResponse(
                            result = "Review created successfully",
                            statusMessage = "success"
                        )
                    )
                } else {
                    // Failed to create review or other error occurred
                    callback(ApiResponse(result = null, statusMessage = "failed"))
                }
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                callback(ApiResponse(result = null, statusMessage = t.message.toString()))
            }
        })
    }

    override fun delete_review(
        movieId: String,
        token: String,
        profileModel: ProfileModel,
        callback: (ApiResponse<String>) -> Unit
    ) {
        val api = retrofit.create(MovieAPI::class.java)

        val deleteReviewCall: Call<String> = api.delete_Review(movieId, token)

        deleteReviewCall.enqueue(object : Callback<String> {
            override fun onResponse(call: Call<String>, response: Response<String>) {
                if (response.isSuccessful) {
                    callback(
                        ApiResponse(
                            result = "Review deleted successfully",
                            statusMessage = "success"
                        )
                    )
                } else {
                    // Failed to create review or other error occurred
                    callback(ApiResponse(result = null, statusMessage = "failed"))
                }
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                callback(ApiResponse(result = null, statusMessage = t.message.toString()))
            }
        })
    }

    override fun getReviewsForMovie(
        movieId: String,
        token: String,
        callback: (ApiResponse<List<ReviewModel>>) -> Unit
    ) {
        val api = retrofit.create(MovieAPI::class.java)

        val getReviewsCall: Call<List<ReviewModel>> = api.getReviewsForMovie(movieId, token)

        getReviewsCall.enqueue(object : Callback<List<ReviewModel>> {
            @SuppressLint("SuspiciousIndentation")
            override fun onResponse(
                call: Call<List<ReviewModel>>,
                response: Response<List<ReviewModel>>
            ) {
                if (response.isSuccessful) {
                    val reviews = response.body()
                    callback(ApiResponse(result = reviews, statusMessage = "success"))

                } else {
                    // Failed to fetch reviews or other error occurred
                    callback(ApiResponse(result = null, statusMessage = "failed"))
                }
            }

            override fun onFailure(call: Call<List<ReviewModel>>, t: Throwable) {
                callback(ApiResponse(result = null, statusMessage = t.message.toString()))
            }
        })
    }

    override fun getOwnReviews(
        token: String,
        callback: (ApiResponse<List<ReviewModel>>) -> Unit
    ) {
        val api = retrofit.create(MovieAPI::class.java)

        val getReviewsCall: Call<List<ReviewModel>> = api.getOwnReviews(token)

        getReviewsCall.enqueue(object : Callback<List<ReviewModel>> {
            @SuppressLint("SuspiciousIndentation")
            override fun onResponse(
                call: Call<List<ReviewModel>>,
                response: Response<List<ReviewModel>>
            ) {
                if (response.isSuccessful) {
                    val reviews = response.body()
                    callback(ApiResponse(result = reviews, statusMessage = "success"))

                } else {
                    // Failed to fetch reviews or other error occurred
                    callback(ApiResponse(result = null, statusMessage = "failed"))
                }
            }

            override fun onFailure(call: Call<List<ReviewModel>>, t: Throwable) {
                callback(ApiResponse(result = null, statusMessage = t.message.toString()))
            }
        })
    }

    override fun getOtherUserReviews(
        userId: String,
        token: String,
        callback: (ApiResponse<List<ReviewModel>>) -> Unit
    ) {
        val api = retrofit.create(MovieAPI::class.java)

        val getReviewsCall: Call<List<ReviewModel>> = api.getOtherUserReviews(userId, token)

        getReviewsCall.enqueue(object : Callback<List<ReviewModel>> {
            @SuppressLint("SuspiciousIndentation")
            override fun onResponse(
                call: Call<List<ReviewModel>>,
                response: Response<List<ReviewModel>>
            ) {
                if (response.isSuccessful) {
                    val reviews = response.body()
                    callback(ApiResponse(result = reviews, statusMessage = "success"))

                } else {
                    // Failed to fetch reviews or other error occurred
                    callback(ApiResponse(result = null, statusMessage = "failed"))
                }
            }

            override fun onFailure(call: Call<List<ReviewModel>>, t: Throwable) {
                callback(ApiResponse(result = null, statusMessage = t.message.toString()))
            }
        })
    }

}
