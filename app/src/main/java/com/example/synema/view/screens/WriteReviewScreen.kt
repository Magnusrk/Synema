package com.example.synema.view.screens

import GradientBox
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.synema.Data.DependencyProvider
import com.example.synema.R
import com.example.synema.model.MovieModel
import com.example.synema.model.ProfileModel
import com.example.synema.view.components.BottomBar
import com.example.synema.view.components.InlineIcon
import com.example.synema.view.components.MainContainer
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
            MainContainer(hasBottomNav = true) {
                TopBar(
                    title = "Review ${movie.title}",
                    alignment = Alignment.Center,
                    backArrow = true,
                    navController = navController,
                    fontSize = 15.sp
                )
                Button( onClick = {},
                    shape = RoundedCornerShape(20),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF75146B)),
                    contentPadding = PaddingValues(horizontal = 5.dp),
                    modifier = Modifier
                        .padding(15.dp)
                        .size(width = 180.dp, height = 50.dp)
                ){
                    RatingStars(1)
                }

                ReviewBox()

                Spacer(modifier = Modifier.height(10.dp))

                Box(modifier = Modifier.fillMaxSize()) {
                    Row (horizontalArrangement = Arrangement.SpaceEvenly, modifier = Modifier.fillMaxWidth()){
                        Button(
                            onClick = { navController.navigate("mediaDetails/" + movie.id + "/review") },
                            shape = RoundedCornerShape(20),
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFd60202)),
                            contentPadding = PaddingValues(horizontal = 15.dp),
                            modifier = Modifier.size(width = 150.dp, height = 50.dp)
                        ) {
                            Text(text = "Delete", fontSize = 20.sp)
                        }
                        Button(
                            onClick = { navController.navigate("mediaDetails/" + movie.id + "/review") },
                            shape = RoundedCornerShape(20),
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4399FF)),
                            contentPadding = PaddingValues(horizontal = 15.dp),
                            modifier = Modifier.size(width = 150.dp, height = 50.dp)
                        ) {
                            Text(text = "Publish", fontSize = 20.sp)
                        }
                    }

            }
            }
            BottomBar(navController = navController)

        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReviewBox(){
    var text by remember { mutableStateOf("") }
    Box(modifier= Modifier
        .fillMaxWidth()
        .height(400.dp)
        .padding(horizontal = 15.dp, vertical = 10.dp)
        .background(Color.White, shape = RoundedCornerShape(4.dp)),
    )
    {
        OutlinedTextField(
            value = text,
            onValueChange = { text = it },
            label = { Text("Label") },
            modifier = Modifier
                .padding(horizontal = 10.dp, vertical = 10.dp)
                .fillMaxSize()
        )
    }
}

@Composable
private fun RatingStars(rating : Number){
    Row ( horizontalArrangement = Arrangement.SpaceEvenly){
        for( n  in 1..5){
            if(rating.toFloat()/2 >= n.toFloat()){
                InlineIcon(resourceID = R.drawable.icon_star, size = 30.dp, spacing = 4.dp, tint= Color(0xFF4399FF))
            } else{
                InlineIcon(resourceID = R.drawable.icon_star, size = 30.dp, spacing = 4.dp)
            }
        }
    }
}

