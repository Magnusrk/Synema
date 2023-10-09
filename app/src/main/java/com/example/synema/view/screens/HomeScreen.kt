package com.example.synema.view.screens

import GradientBox
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.synema.Data.Datasource
import com.example.synema.R
import com.example.synema.model.Movie
import com.example.synema.ui.theme.SynemaTheme
import com.example.synema.view.components.TopBar

@Preview
@Composable
private fun MovieCardPreview() {
    MovieCard(Movie(R.string.movie1, R.drawable.image1))
}

@Composable
public fun HomeScreen(navController : NavHostController) {
    SynemaTheme {
        // A surface container using the 'background' color from the theme
            GradientBox() {
                MoviesApp()
            }
    }
}

@Preview
@Composable
fun MoviesApp() {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        //TopBar("My Watchlist", Alignment.Center, 30.sp, true, false)
        TopBar("SYNEMA", Alignment.CenterStart, 20.sp, false, true)

        MovieList(
            movieList = Datasource().loadMovies(),
            header = "For you"
        )
        MovieList(
            movieList = Datasource().loadMovies(),
            header = "Trending"
        )
        MovieList(
            movieList = Datasource().loadMovies(),
            header = "Horror"
        )
        MovieList(
            movieList = Datasource().loadMovies(),
            header = "Anime"
        )
    }

}

@Composable
fun MovieList(movieList: List<Movie>, modifier: Modifier = Modifier, header: String) {

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
                    modifier = Modifier.padding(8.dp)
                )

            }
        }
    }
}


@Composable
fun MovieCard(movie: Movie, modifier: Modifier = Modifier) {
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
                    .height(135.dp),
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