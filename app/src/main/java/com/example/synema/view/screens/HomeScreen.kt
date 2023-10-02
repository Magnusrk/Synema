package com.example.synema.view.screens

import GradientBox
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
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
    Column {
        TopBar("My Watchlist", Alignment.Center, 30.sp)
        //TopBar("SYNEMA", Alignment.CenterStart, 20.sp)

        MovieList(
            movieList = Datasource().loadMovies(),
        )
    }

}

@Composable
fun MovieList(movieList: List<Movie>, modifier: Modifier = Modifier) {
    LazyRow(modifier = modifier) {
        items(movieList) { movie ->
            MovieCard(
                movie = movie,
                modifier = Modifier.padding(8.dp)
            )

        }
    }
}


@Composable
fun MovieCard(movie: Movie, modifier: Modifier = Modifier) {
    Card (modifier = modifier) {
        Column {
            Image(
                painter = painterResource(movie.imageResourceId),
                contentDescription = stringResource(movie.stringResourceId),
                modifier = Modifier
                    .width(95.dp)
                    .height(135.dp),
                contentScale = ContentScale.FillBounds
            )
            Text(
                text = LocalContext.current.getString(movie.stringResourceId),
                modifier = Modifier.padding(15.dp),
                style = MaterialTheme.typography.labelMedium
            )
        }

    }

}