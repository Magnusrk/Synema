package com.example.synema.view.screens

import GradientBox
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.synema.controller.AppContext
import com.example.synema.model.MovieModel
import com.example.synema.ui.theme.SynemaTheme
import com.example.synema.view.components.BottomBar
import com.example.synema.view.components.LoadingWrapper
import com.example.synema.view.components.MainContainer
import com.example.synema.view.components.TopBar
import com.example.synema.view.components.TrendTopBar
import com.example.synema.viewmodel.media.HomeViewModel




@Composable
fun HomeScreen() {
    val homeViewModel : HomeViewModel = viewModel()
    println("Token: " + AppContext.getInstance().getProfileState().value.token)
    homeViewModel.loadMovies()
    SynemaTheme {
        // A surface container using the 'background' color from the theme
            GradientBox {
                LoadingWrapper(homeViewModel.isLoading, hasLogo = true) {
                    MoviesApp(homeViewModel = homeViewModel)
                }

            }
    }
}


@Composable
fun MoviesApp(homeViewModel: HomeViewModel) {


    Column (modifier = Modifier.fillMaxSize()){
        TopBar("Synema", Alignment.CenterStart, 30.sp, search = true, navController = homeViewModel.getNav(), transparent = true)
        MainContainer(hasBottomNav = true) {

                TrendTopBar(homeViewModel.discoverList, homeViewModel.getNav())

                MovieList(
                    movieList = homeViewModel.newList,
                    header = "New releases",
                    navController = AppContext.getInstance().getNav()
                )
                MovieList(
                    movieList = homeViewModel.discoverList,
                    header = "For you",
                    navController = homeViewModel.getNav()
                )
                MovieList(
                    movieList = homeViewModel.comedyList,
                    header = "Comedy",
                    navController = homeViewModel.getNav()
                )
                MovieList(
                    movieList = homeViewModel.horrorList,
                    header = "Horror",
                    navController = homeViewModel.getNav()
                )

            MovieList(
                movieList = homeViewModel.animeList,
                header = "Animation",
                navController = homeViewModel.getNav()
            )
            MovieList(
                movieList = homeViewModel.historyList,
                header = "History",
                navController = homeViewModel.getNav()
            )
        }
        BottomBar(homeViewModel.getNav())
    }


}



@Composable
private fun MovieList(movieList: List<MovieModel>, modifier: Modifier = Modifier, header: String, navController : NavHostController) {

    var movList = movieList.shuffled();
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
            items(key = {
                movList[it % movList.size].id
            }, count = Int.MAX_VALUE) { index ->
                    val item = movList[index % movList.size]
                MovieCard(
                    movie = item,
                    modifier = Modifier.padding(8.dp),
                    navController
                )
            }
        }
    }
}


@Composable
private fun MovieCard(movie: MovieModel, modifier: Modifier = Modifier, navController : NavHostController) {
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


