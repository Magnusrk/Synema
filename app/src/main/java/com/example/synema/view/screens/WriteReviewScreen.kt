package com.example.synema.view.screens

import GradientBox
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.synema.Data.DependencyProvider
import com.example.synema.model.MovieModel
import com.example.synema.model.ProfileModel
import com.example.synema.view.components.BottomBar
import com.example.synema.view.components.TopBar

@Composable
fun WriteReviewScreen(navController : NavHostController, profileState: MutableState<ProfileModel>, movieID: String?) {
    val watchlistDataSource = DependencyProvider.getInstance().getWatchlistSource();
    var movie : MovieModel by remember {
        mutableStateOf(
            MovieModel(
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
    val movieDataSource = DependencyProvider.getInstance().getMovieSource();

    if (movieID != null) {
        movieDataSource.loadMovie(movieID){
            movie = it.getResult()!!
        }
    }

    GradientBox {
        Column {
            TopBar(title = "Review ${movie.title}", alignment = Alignment.Center, backArrow = true, navController = navController, fontSize = 15.sp)
            BottomBar(navController = navController)
        }
    }
}