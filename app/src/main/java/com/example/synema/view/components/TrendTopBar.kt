package com.example.synema.view.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TrendTopBar(
    movies:List<MovieModel>,
    navController: NavController
) {

    val state = rememberLazyListState()


    LazyRow(
        modifier = Modifier
            .fillMaxWidth() // set the width to the maximum available space
            .height(272.dp), // Adjust the height as needed
        // .background(Color.Transparent) // You can set a transparent background
        state = state,
        flingBehavior = rememberSnapFlingBehavior(lazyListState = state)


    ) {
        items(movies) {movie ->
            val imageModifier = Modifier
                .size(400.dp)
                .clickable { navController.navigate("mediaDetails/" + movie.id) }
            BoxWithConstraints {
                AsyncImage(
                    model = movie.backdrop_url,
                    contentDescription = null,
                    modifier = imageModifier,
                    contentScale = ContentScale.Crop
                )
                Box(
                    modifier = Modifier
                        .width(maxWidth)
                        .background(Color.Black.copy(alpha = 0.6f))
                        .align(Alignment.BottomStart)
                ) {
                    Text(text = movie.title, color = Color.White, modifier = Modifier.padding(start = 5.dp, end = 5.dp))
                }
            }

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

