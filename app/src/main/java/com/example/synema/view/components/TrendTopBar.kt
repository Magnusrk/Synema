package com.example.synema.view.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.synema.R
import com.example.synema.model.MovieModel

/*
val moviePosters = listOf(
    R.drawable.intersteller,
    R.drawable.movieposter
)
*/
@Composable
fun TrendTopBar(
    movies:List<MovieModel>,
    search: Boolean = false,
    navController: NavController
) {
    //var selectedImageIndex by remember {mutableStateOf(0)}

    LazyRow(
        modifier = Modifier
            .fillMaxWidth() // set the width to the maximum available space
            .height(272.dp) // Adjust the height as needed
        // .background(Color.Transparent) // You can set a transparent background

    ) {
        items(movies) {movie ->
            val imageModifier = Modifier
                .size(400.dp)
                .clickable { navController.navigate("mediaDetails/" + movie.id) }
            AsyncImage(
                model = movie.backdrop_url,
                contentDescription = null,
                modifier = imageModifier,
                contentScale = ContentScale.Crop
            )
        }

    }
}


// working trend top bar unscrollable
/*
@Composable
fun TrendTopBar(
   // search: Boolean = false,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth() // set the width to the maximum available space
            .height(272.dp) // Adjust the height as needed
           // .background(Color.Transparent) // You can set a transparent background

    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            val imageModifier = Modifier
                .size(400.dp)
            Image(
                //adds image
                painter = painterResource(id = R.drawable.intersteller),
                contentDescription = null,
                contentScale = ContentScale.Crop, //crop image to fit the container
                modifier = imageModifier // apply the imagemodifier to the image
            )
        }
    }
}*/

