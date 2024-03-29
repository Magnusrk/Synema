package com.example.synema.view.screens

import GradientBox
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.synema.Data.DependencyProvider
import com.example.synema.model.MovieModel
import com.example.synema.model.ProfileModel
import com.example.synema.ui.theme.SynemaTheme
import com.example.synema.view.components.LoadingWrapper
import com.example.synema.view.components.TopBar
import com.example.synema.viewmodel.SearchViewModel

@Composable
 fun SearchScreen() {

    var vm : SearchViewModel = viewModel()
    vm.initSearch()
    SynemaTheme {
        GradientBox() {
            MovieList(vm)
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun MovieList(vm : SearchViewModel) {
    Column(
        modifier = Modifier
            .fillMaxSize()

    ) {
        TopBar("", Alignment.CenterStart, 20.sp, backArrow = true, transparent = true, search = false, textInput = true, navController = vm.getNav(), onChange = {
           vm.search(it)
        },
            inputLabel = "Search movie", filter = false, filterPopUp = {vm.filterPopUp.value = !vm.filterPopUp.value})
        LoadingWrapper(vm.isLoading) {
            if(vm.filterPopUp.value){
                FilterPopUp()
            }
            LazyVerticalGrid(
                columns = GridCells.Adaptive(minSize = 128.dp)
            ){
                items(vm.movieList.size) { index ->
                    MovieCard(
                        movie = vm.movieList[index],
                        modifier = Modifier.padding(8.dp),
                        vm.getNav()
                    )
                }
            }

        }

    }
}


@Composable
fun FilterPopUp(){
    Box(modifier = Modifier.fillMaxSize().padding(10.dp),
        contentAlignment = Alignment.Center

    ){
        Column(modifier = Modifier.background(color = Color(0xFF430B3D), shape = RoundedCornerShape(5.dp)).height(200.dp).fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Filter movies", color = Color.White, fontSize = 25.sp)
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


