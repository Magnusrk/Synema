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
import androidx.compose.ui.text.style.TextOverflow
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
        state = state,
        flingBehavior = rememberSnapFlingBehavior(lazyListState = state),



    ) {
        items(key = {
            movies[it % movies.size].id
        },count = Int.MAX_VALUE) { index ->
            val movie = movies[index % movies.size]
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
                    Text(text = movie.title, color = Color.White, modifier = Modifier.padding(start = 15.dp, end = 15.dp).fillMaxWidth(0.8f),overflow = TextOverflow.Ellipsis)
                }
            }

        }

    }
}


