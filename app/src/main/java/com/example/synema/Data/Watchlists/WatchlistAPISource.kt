import android.util.Log
import com.example.synema.controller.WatchlistAPI
import com.example.synema.Data.Watchlists.WatchlistDataSource
import com.example.synema.controller.MovieAPI
import com.example.synema.model.ApiResponse
import com.example.synema.model.MovieModel
import com.example.synema.model.WatchlistModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class WatchlistAPISource: WatchlistDataSource {
    //private val BASE_URL = "http://192.168.0.107:8000"
    val BASE_URL = "https://cwjtedqahp.eu10.qoddiapp.com/"

    val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    override fun createWatchlist(watchlistName: String, token : String, callback: (ApiResponse<MovieModel>) -> Unit) {
        if (watchlistName.isEmpty()) {
            // Handle the case where the watchlist name is empty
            // You can throw an exception or handle it as needed
            createWatchlist("Hardcodedname", token,  callback)
            return
        }
        val api = retrofit.create(WatchlistAPI::class.java)

        val createWatchlistCall: Call<WatchlistModel> = api.createWatchlist(WatchlistModel(watchlistName,"","",
            listOf(), listOf()
        ), token)

        createWatchlistCall.enqueue(object : Callback<WatchlistModel> {
            override fun onResponse(call: Call<WatchlistModel>, response: Response<WatchlistModel>) {
                if (response.isSuccessful) {
                    callback(ApiResponse(result = null, statusMessage = "success"))
                } else {
                    // Watchlist creation failed
                    callback(ApiResponse(result = null, statusMessage = "failed1"))

                }
            }

            override fun onFailure(call: Call<WatchlistModel>, t: Throwable) {
                callback(ApiResponse(result = null, statusMessage = t.message.toString()))

            }
        })
    }

    override fun read_db(token : String, callback: (ApiResponse<List<WatchlistModel>>) -> Unit) {
        val api = retrofit.create(WatchlistAPI::class.java)
        Log.d("Main", token);

        val getAllWatchlistsCall: Call<List<WatchlistModel>> = api.read_db(token)

        getAllWatchlistsCall.enqueue(object : Callback<List<WatchlistModel>> {
            override fun onResponse(call: Call<List<WatchlistModel>>, response: Response<List<WatchlistModel>>) {
                if (response.isSuccessful) {
                    callback(ApiResponse(result = response.body()!!, statusMessage = "success"))
                } else {
                    // Failed to fetch watchlists
                    callback(ApiResponse(result = null, statusMessage = "failed", error = true))
                }
            }

            override fun onFailure(call: Call<List<WatchlistModel>>, t: Throwable) {
                callback(ApiResponse(result = null, statusMessage = t.message.toString(),error=true))
            }
        })
    }

    override fun read_otherUsers_db(userId : String, token : String, callback: (ApiResponse<List<WatchlistModel>>) -> Unit) {
        val api = retrofit.create(WatchlistAPI::class.java)
        Log.d("Main", token);

        val getAllWatchlistsCall: Call<List<WatchlistModel>> = api.read_otherUsers_db(userId, token)

        getAllWatchlistsCall.enqueue(object : Callback<List<WatchlistModel>> {
            override fun onResponse(call: Call<List<WatchlistModel>>, response: Response<List<WatchlistModel>>) {
                if (response.isSuccessful) {
                    callback(ApiResponse(result = response.body()!!, statusMessage = "success"))
                } else {
                    // Failed to fetch watchlists
                    callback(ApiResponse(result = null, statusMessage = "failed", error = true))
                }
            }

            override fun onFailure(call: Call<List<WatchlistModel>>, t: Throwable) {
                callback(ApiResponse(result = null, statusMessage = t.message.toString(),error=true))
            }
        })
    }



     override fun getWatchlistById(watchlistId: String,token: String, callback: (ApiResponse<WatchlistModel>) -> Unit) {
        val api = retrofit.create(WatchlistAPI::class.java)

        val getWatchlistByIdCall: Call<WatchlistModel> = api.getWatchlistById(watchlistId,token
        )

        getWatchlistByIdCall.enqueue(object : Callback<WatchlistModel> {
            override fun onResponse(call: Call<WatchlistModel>, response: Response<WatchlistModel>) {
                if (response.isSuccessful) {
                    callback(ApiResponse(result = response.body(), statusMessage = "success"))
                } else {
                    // Watchlist not found or other error
                    callback(ApiResponse(result = null, statusMessage = "failed"))
                }
            }

            override fun onFailure(call: Call<WatchlistModel>, t: Throwable) {
                callback(ApiResponse(result = null, statusMessage = t.message.toString()))
            }
        })
    }


    override fun deleteWatchlist(watchlistId: String, token: String, callback: (ApiResponse<String>) -> Unit
    ) {
        val api = retrofit.create(WatchlistAPI::class.java)

        val deleteWatchlistCall: Call<String> = api.delete_watchlist(watchlistId, token)

        deleteWatchlistCall.enqueue(object : Callback<String> {
            override fun onResponse(
                call: Call<String>,
                response: Response<String>
            ) {
                if (response.isSuccessful) {
                    // Watchlist deleted successfully
                    callback(
                        ApiResponse(
                            result = "Watchlist deleted successfully",
                            statusMessage = "success"
                        )
                    )

                } else {
                    // If deletion of watchlist failed
                    callback(ApiResponse(result = null, statusMessage = "failed"))
                }
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                callback(ApiResponse(result = null, statusMessage = t.message.toString()))
            }
        })
    }
    override fun addMovieToWatchlist(watchlistId: String, movieId: String, token : String,  callback: (ApiResponse<String>) -> Unit) {
        val api = retrofit.create(WatchlistAPI::class.java)

        val addMovieCall: Call<String> = api.addMovieToWatchlist(watchlistId, movieId, token)

        addMovieCall.enqueue(object : Callback<String> {
            override fun onResponse(call: Call<String>, response: Response<String>) {
                if (response.isSuccessful) {
                    callback(ApiResponse(result = "Movie added successfully", statusMessage = "success"))
                } else {
                    // Failed to add movie or watchlist not found
                    callback(ApiResponse(result = null, statusMessage = "failed"))
                }
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                callback(ApiResponse(result = null, statusMessage = t.message.toString()))
            }
        })
    }

    override fun deleteMovieFromWatchlist(watchlistId: String, movieId: String,token: String, callback: (ApiResponse<String>) -> Unit) {
        val api = retrofit.create(WatchlistAPI::class.java)

        val deleteMovieCall: Call<String> = api.deleteMovieFromWatchlist(watchlistId, movieId,token)

        deleteMovieCall.enqueue(object : Callback<String> {
            override fun onResponse(call: Call<String>, response: Response<String>) {
                if (response.isSuccessful) {
                    callback(ApiResponse(result = "Movie deleted successfully", statusMessage = "success"))
                } else {
                    // Failed to delete movie or watchlist not found
                    callback(ApiResponse(result = null, statusMessage = "failed"))
                }
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                callback(ApiResponse(result = null, statusMessage = t.message.toString()))
            }
        })
    }
}





