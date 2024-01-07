package com.example.synema.view.screens
import GradientBox
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.synema.Data.DependencyProvider
import com.example.synema.R
import com.example.synema.model.MovieModel
import com.example.synema.model.ProfileModel
import com.example.synema.model.WatchlistModel
import com.example.synema.view.components.BottomBar
import com.example.synema.view.components.MainContainer
import com.example.synema.view.components.TopBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyListScreen(
    navController: NavHostController,
    profileState: MutableState<ProfileModel>,
    watchlistID: String?
) {
    val source = DependencyProvider.getInstance().getMovieSource();
    val dataSource = DependencyProvider.getInstance().getWatchlistSource();

    var watchlistName by remember { mutableStateOf("") }
    var watchlist : WatchlistModel by remember {
        mutableStateOf(WatchlistModel("",
            "",
            "",
            emptyList(),
            emptyList()
        ))
    }

    dataSource.getWatchlistById(watchlistID.toString(),) {
        if (it.successful()) {
            it.getResult()?.let {watchlistModel ->
                watchlist = watchlistModel
            }
            println(watchlist)
        }
    }
    var movie1 : MovieModel by remember {
        mutableStateOf(MovieModel(
            0,
            "",
            "",
            "Loading...",
            "Loading...",
            0,
            ""
        )
        )
    }
    GradientBox {
        Column {
            MainContainer(hasBottomNav = true) {
                TopBar(title = "My List", alignment = Alignment.Center)
                watchlist.movieIds.forEach { movie ->
                        source.loadMovie(movie) {
                           var movie1 = it.getResult()
                        }
                    println(movie1.id)
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(color = Color(0xFF191825)),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            AsyncImage(
                                model = movie1.poster_url,
                                contentDescription = null,
                                modifier = Modifier
                                    .width(95.dp)
                                    .height(135.dp)
                                    .clickable { navController.navigate("mediaDetails/" + movie1.id) },
                                contentScale = ContentScale.FillBounds
                            )
                            Text(
                                text = movie1.title,
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.White,
                                modifier = Modifier.size(200.dp, 98.dp)
                            )
                            Surface(
                                modifier = Modifier.size(30.dp),
                                color = Color(0, 0, 0, 0),
                                onClick = { navController?.popBackStack() }) {
                                Image(
                                    painter = painterResource(id = R.drawable.heart),
                                    contentDescription = null,
                                    modifier = Modifier.size(30.dp)
                                )
                            }
                            Surface(
                                modifier = Modifier.size(30.dp),
                                color = Color(0, 0, 0, 0),
                                onClick = { navController?.popBackStack() }) {
                                Image(
                                    painter = painterResource(id = R.drawable.edit_playlist),
                                    contentDescription = null,
                                    modifier = Modifier.size(30.dp)
                                )
                            }
                        }
                    }

                }

                BottomBar(navController = navController)
            }
        }
    }
