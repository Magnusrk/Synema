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
    val BASE_URL = "https://cwjtedqahp.eu10.qoddiapp.com/"

    val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    override fun createWatchlist(watchlistName: String , callback: (ApiResponse<MovieModel>) -> Unit) {
        if (watchlistName.isEmpty()) {
            // Handle the case where the watchlist name is empty
            // You can throw an exception or handle it as needed
            createWatchlist("Hardcodedname", callback)
            return
        }
        val api = retrofit.create(WatchlistAPI::class.java)

        val createWatchlistCall: Call<WatchlistModel> = api.createWatchlist(WatchlistModel(watchlistName,"","",
            listOf()
        ))

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

    override fun read_db(callback: (ApiResponse<List<WatchlistModel>>) -> Unit) {
        val api = retrofit.create(WatchlistAPI::class.java)

        val getAllWatchlistsCall: Call<List<WatchlistModel>> = api.read_db()

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



     override fun getAllWatchlists(callback: (ApiResponse<List<WatchlistModel>>) -> Unit) {
        val api = retrofit.create(WatchlistAPI::class.java)

        val getAllWatchlistsCall: Call<List<WatchlistModel>> = api.read_db()

        getAllWatchlistsCall.enqueue(object : Callback<List<WatchlistModel>> {
            override fun onResponse(call: Call<List<WatchlistModel>>, response: Response<List<WatchlistModel>>) {
                if (response.isSuccessful) {
                    callback(ApiResponse(result = response.body(), statusMessage = "success"))
                } else {
                    // Failed to fetch watchlists
                    callback(ApiResponse(result = null, statusMessage = "failed"))
                }
            }

            override fun onFailure(call: Call<List<WatchlistModel>>, t: Throwable) {
                callback(ApiResponse(result = null, statusMessage = t.message.toString()))
            }
        })
    }

     override fun addMovieToWatchlist(watchlistId: String, movieId: String, callback: (ApiResponse<String>) -> Unit) {
        val api = retrofit.create(WatchlistAPI::class.java)

        val addMovieCall: Call<String> = api.addMovieToWatchlist(watchlistId, movieId)

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




}
