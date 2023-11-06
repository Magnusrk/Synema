package com.example.synema.view.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.synema.R


val moviePosters = listOf(
    R.drawable.intersteller,
    R.drawable.movieposter
)

@Composable
fun TrendTopBar(
    movieImg: List<Int>
    // search: Boolean = false,
) {
    //var selectedImageIndex by remember {mutableStateOf(0)}

    LazyRow(
        modifier = Modifier
            .fillMaxWidth() // set the width to the maximum available space
            .height(272.dp) // Adjust the height as needed
        // .background(Color.Transparent) // You can set a transparent background

    ) {
        items(movieImg) {movieImg ->
            val imageModifier = Modifier
                .size(400.dp)
            val painter = painterResource(id = movieImg)

            Image(
                //adds image
                painter = painter,
                contentDescription = null,
                contentScale = ContentScale.Crop, //crop image to fit the container
                modifier = imageModifier // apply the image modifier to the image
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

