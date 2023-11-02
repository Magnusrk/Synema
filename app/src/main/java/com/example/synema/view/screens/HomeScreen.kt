package com.example.synema.view.screens

import GradientBox
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.synema.Data.DependencyProvider
import com.example.synema.Data.movies.MockMovieDataSource
import com.example.synema.R
import com.example.synema.model.MovieModel
import com.example.synema.model.ProfileModel
import com.example.synema.ui.theme.SynemaTheme
import com.example.synema.view.components.BottomBar
import com.example.synema.view.components.MainContainer
import com.example.synema.view.components.TopBar
import com.example.synema.view.components.TrendTopBar

/*
@Preview
@Composable
private fun MovieCardPreview() {
    MovieCard(MovieModel(R.string.movie1, R.drawable.image1))
}
*/
@Composable
public fun HomeScreen(navController : NavHostController, profileState : MutableState<ProfileModel>) {
    SynemaTheme {
        // A surface container using the 'background' color from the theme
            GradientBox() {
                MoviesApp(navController, profileState)
            }


    }
}


@Composable
fun MoviesApp(navController : NavHostController, profileState: MutableState<ProfileModel>) {

    val dataSource = DependencyProvider.getInstance().getMovieSource();

    Column (modifier = Modifier.fillMaxSize()){
        MainContainer(hasBottomNav = true) {
            TrendTopBar(R.drawable.intersteller, search = true, backArrow = true, navController = navController)
            MovieList(
                movieList = dataSource.loadMovies(),
                header = "For you",
                navController = navController
            )
            MovieList(
                movieList = dataSource.loadMovies(),
                header = "Trending",
                navController = navController
            )
            MovieList(
                movieList = dataSource.loadMovies(),
                header = "Horror",
                navController = navController
            )
            MovieList(
                movieList = dataSource.loadMovies(),
                header = "Anime",
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
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Image(
                painter = painterResource(movie.imageResourceId),
                contentDescription = stringResource(movie.stringResourceId),
                modifier = Modifier
                    .width(95.dp)
                    .height(135.dp)
                    .clickable { navController.navigate("mediaDetails/" + movie.id) }
                ,
                contentScale = ContentScale.FillBounds
            )
            Spacer(modifier =  Modifier.height(5.dp))
            Text(
                text = LocalContext.current.getString(movie.stringResourceId),
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
            )
        }

    }



}


