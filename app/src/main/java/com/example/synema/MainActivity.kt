package com.example.synema

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.synema.view.screens.LoginScreen
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.synema.ui.theme.SynemaTheme
import com.example.synema.model.Movie
import androidx.compose.material3.Card
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.Image
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.ui.unit.dp
import androidx.compose.ui.layout.ContentScale
import androidx.compose.material3.Text
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.foundation.lazy.items
import com.example.synema.Data.Datasource


class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



        setContent {
            val navController = rememberNavController()
                // A surface container using the 'background' color from the theme
            NavHost(navController = navController, startDestination = "login") {
                    composable("login") { LoginScreen(navController) }


                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MoviesApp()
                }
            }
        }
    }








}


@Preview
@Composable
private fun MovieCardPreview() {
    MovieCard(Movie(R.string.movie1, R.drawable.image1))
}

@Preview
@Composable
fun MoviesApp() {
    MovieList(
        movieList = Datasource().loadMovies(),
    )
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