package com.example.synema.view.screens

import GradientBox
import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MenuItemColors
import androidx.compose.material3.Slider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.synema.R
import com.example.synema.controller.AppContext
import com.example.synema.model.GenreModel
import com.example.synema.model.MovieModel
import com.example.synema.ui.theme.SynemaTheme
import com.example.synema.view.components.BottomBar
import com.example.synema.view.components.LoadingWrapper
import com.example.synema.view.components.MainContainer
import com.example.synema.view.components.TopBar
import com.example.synema.view.components.TrendTopBar
import com.example.synema.viewmodel.media.HomeViewModel
import java.time.LocalDate
import java.time.temporal.TemporalField
import java.time.temporal.ValueRange
import kotlin.time.Duration.Companion.milliseconds


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
            CustomList(homeViewModel)

        }
        BottomBar(homeViewModel.getNav())
    }


}

@Composable
private fun CustomList(vm : HomeViewModel){
    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxWidth()) {


        if(!vm.hasFiltered.value){
            Text("What are you feeling like today?", color= Color.White, fontSize = 20.sp, fontWeight = FontWeight.Bold)
            Text("Find which movie to watch today", color= Color.White, fontSize = 12.sp)
            GenreDropDown(vm)
            GenreCards(vm)
            MinimumRatingSlider(vm)
            SubmitButton(vm)
        }
        else{
            Column(modifier = Modifier.height(400.dp), horizontalAlignment = Alignment.CenterHorizontally){
                LoadingWrapper(vm.filterLoad) {
                    MovieList(movieList = vm.filteredList, header = "Suggested movies", navController = vm.getNav() )
                }
                SearchAgainButton(vm)

            }

        }




    }
}


@Composable
private fun SubmitButton(vm : HomeViewModel){
    Button(onClick = { vm.filterSearch()}, modifier = Modifier.padding(10.dp)){
        Text("Search movies", color = Color.White)
    }
}

@Composable
private fun SearchAgainButton(vm : HomeViewModel){
    Button(onClick = { vm.hasFiltered.value = false }, modifier = Modifier.padding(10.dp)){
        Text("Change search", color = Color.White)
    }
}

@Composable
private fun MinimumRatingSlider(vm : HomeViewModel){

    Column(
        Modifier
            .padding(horizontal = 10.dp)
            .fillMaxWidth()
            .padding(top = 15.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        Text("Minimum rating", color= Color.White, fontSize = 12.sp, fontWeight = FontWeight.Bold)
        Slider(
            modifier = Modifier.width(200.dp),
            value = vm.minRating.value,
            onValueChange = { vm.minRating.value = it },
            valueRange = 0.1f..9.9f,
        )
    }


    Column (modifier = Modifier.width(150.dp)){
        Box(modifier = Modifier.fillMaxWidth(1f)){
            RatingStars(vm.minRating.value)
        }

    }
}




@Composable
private fun RatingStars(rating: Number) {

    val starImage = ImageBitmap.imageResource(id = R.drawable.stars6)
    //val bigStarImage = ImageBitmap.imageResource(id = R.drawable.stars_big)

    BoxWithConstraints() {

        Box(
            modifier = Modifier
                .height(30.dp)
                .fillMaxWidth(rating.toFloat() / 10)

        ) {
            Canvas(
                modifier = Modifier.fillMaxSize()
            ) {

                drawImage(image = starImage)
            }
            Canvas(
                modifier = Modifier
                    .fillMaxSize()
                    .graphicsLayer(alpha = 0.99f)
            ) {

                drawRect(
                    color = Color(0xFFB6842D),
                    size = size,
                )
                drawImage(image = starImage, blendMode = BlendMode.DstAtop)
            }
        }
    }
}


@Composable
private fun GenreDropDown(vm : HomeViewModel ){
    val expanded = vm.genreExpanded
    val genres = vm.genres

    Row(horizontalArrangement = Arrangement.Center){
        var containerColor = Color(0xFF811C77)
        if(expanded.value){
            containerColor = Color.White
        }
        Button(onClick = { expanded.value = !expanded.value; genres.sortBy { g -> !vm.genreIsSelected(g) } },
            colors = ButtonDefaults.buttonColors(containerColor = containerColor, )) {
            var buttonLabel = "";
            if(expanded.value){
                buttonLabel = "Close"
            } else{
                buttonLabel = "Choose genres"
            }

            var color = Color.White
            if(expanded.value){
                color = Color(0xFF811C77)
            }

            Text(buttonLabel, color = color)
        }
        DropdownMenu(
            expanded = expanded.value,
            onDismissRequest = { expanded.value = false },
            Modifier.height(200.dp),
        ) {
            genres.forEach{ genre ->
                var text = genre.name
                if(vm.genreIsSelected(genre)){
                    text += " ✔"
                }
                DropdownMenuItem(
                    text = { Text(text) },
                    onClick = { vm.toggleGenre(genre) },
                )

            }

        }
    }

}

@Composable
private fun GenreCards(vm : HomeViewModel){
    LazyRow{
        items(vm.filterGenres.size){
            TagCard(vm.filterGenres[it].name, onClick = {vm.toggleGenre(vm.filterGenres[it])})

        }
        item {
            if(vm.filterGenres.size == 0){
                TagCard(text = "No genres selected", onClick = {vm.genreExpanded.value = true})
            }
        }

    }
}

@Composable
private fun TagCard(text : String, onClick : () -> Unit){

    Card (modifier = Modifier.padding(5.dp).clickable { onClick() }, colors = CardDefaults.cardColors(
        containerColor = Color(0xFF243988)
    )){
        Text(text, modifier = Modifier.padding(8.dp), color = Color.White, fontSize = 12.sp)
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


