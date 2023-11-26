package com.example.synema.view.screens

import GradientBox
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.synema.Data.DependencyProvider
import com.example.synema.Data.movies.MockMovieDataSource
import com.example.synema.model.MovieModel
import com.example.synema.model.ProfileModel
import com.example.synema.ui.theme.SynemaTheme
import com.example.synema.view.components.BottomBar
import com.example.synema.view.components.MainContainer
import com.example.synema.view.components.TopBar
import com.example.synema.view.components.TrendTopBar


@Composable
fun HomeScreen(navController : NavHostController, profileState : MutableState<ProfileModel>) {
    SynemaTheme {
        // A surface container using the 'background' color from the theme
            GradientBox {
                MoviesApp(navController, profileState)
            }
    }
}


@Composable
fun MoviesApp(navController : NavHostController, profileState: MutableState<ProfileModel>) {

    val dataSource = DependencyProvider.getInstance().getMovieSource();

    var discoverList : List<MovieModel> by remember {
        mutableStateOf(listOf())
    }
    var comedyList : List<MovieModel> by remember {
        mutableStateOf(listOf())
    }

    var horrorList : List<MovieModel> by remember {
        mutableStateOf(listOf())
    }
    var animeList : List<MovieModel> by remember {
        mutableStateOf(listOf())
    }
    var historyList : List<MovieModel> by remember {
        mutableStateOf(listOf())
    }
    dataSource.loadDiscoverMovies (){
        it.getResult()?.let {movieModel ->
            discoverList = movieModel
        }

    }
    dataSource.loadDiscoverMovies(genres = "35") {
        it.getResult()?.let {movieModel ->
            comedyList = movieModel
        }
    }
    dataSource.loadDiscoverMovies(genres = "27") {
        it.getResult()?.let {movieModel ->
            horrorList = movieModel
        }
    }
    dataSource.loadDiscoverMovies(genres = "16") {
        it.getResult()?.let {movieModel ->
            animeList = movieModel
        }
    }
    dataSource.loadDiscoverMovies(genres = "36") {
        it.getResult()?.let {movieModel ->
            historyList = movieModel
        }
    }




    Column (modifier = Modifier.fillMaxSize()){
        MainContainer(hasBottomNav = true) {
            TopBar("Synema", Alignment.CenterStart, 30.sp, search = true, navController = navController)
            TrendTopBar(discoverList,search=true, navController)
            MovieList(
                movieList = discoverList,
                header = "For you",
                navController = navController
            )
            MovieList(
                movieList = comedyList,
                header = "Comedy",
                navController = navController
            )
            MovieList(
                movieList = horrorList,
                header = "Horror",
                navController = navController
            )
            MovieList(
                movieList = animeList,
                header = "Animation",
                navController = navController
            )
            MovieList(
                movieList = historyList,
                header = "History",
                navController = navController
            )
        }
        BottomBar(navController)
    }


}



@Composable
fun MovieList(movieList: List<MovieModel>, modifier: Modifier = Modifier, header: String, navController : NavHostController) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 12.dp)
    ) {
        Text(
            text = header,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            modifier = Modifier
                .padding(8.dp)
        )
        LazyRow(modifier = modifier) {
            items(movieList) { movie ->
                MovieCard(
                    movie = movie,
                    modifier = Modifier.padding(8.dp),
                    navController
                )
            }
        }
    }
}


@Composable
fun MovieCard(movie: MovieModel, modifier: Modifier = Modifier, navController : NavHostController) {
    Surface(
        modifier = modifier,
        shape = RoundedCornerShape(10.dp), // Customize the shape if needed
        color = Color(0x00000000) // Set the color to transparent
    ) {
        Column (
            //verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.width(95.dp)
        ){
            AsyncImage(
                model = movie.poster_url,
                contentDescription = null,
                modifier = Modifier
                    .width(95.dp)
                    .height(135.dp)
                    .clickable { navController.navigate("mediaDetails/" + movie.id) }
                ,
                contentScale = ContentScale.FillBounds
            )

            Spacer(modifier =  Modifier.height(5.dp))
            Text(
                text = movie.title,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                overflow = TextOverflow.Ellipsis,
                maxLines = 2,
                lineHeight = 12.sp,
                textAlign = TextAlign.Center

            )
        }

    }



}


