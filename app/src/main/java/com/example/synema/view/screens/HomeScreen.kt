package com.example.synema.view.screens

import GradientBox
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.synema.Data.Datasource
import com.example.synema.R
import com.example.synema.model.MovieModel
import com.example.synema.model.ProfileModel
import com.example.synema.ui.theme.SynemaTheme

@Preview
@Composable
private fun MovieCardPreview() {
    MovieCard(MovieModel(R.string.movie1, R.drawable.image1))
}

@Composable
public fun HomeScreen(navController : NavHostController, profileState : MutableState<ProfileModel>) {
    SynemaTheme {
        // A surface container using the 'background' color from the theme
        GradientBox(
        ) {
            MoviesApp()
        }
    }
}

@Preview
@Composable
fun MoviesApp() {
    MovieList(
        movieList = Datasource().loadMovies(),
    )
}

@Composable
fun MovieList(movieList: List<MovieModel>, modifier: Modifier = Modifier) {
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
fun MovieCard(movie: MovieModel, modifier: Modifier = Modifier) {
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