package com.example.synema.view.screens

import GradientBox
import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.synema.Data.DependencyProvider
import com.example.synema.R
import com.example.synema.model.MovieModel
import com.example.synema.model.ProfileModel
import com.example.synema.model.ReviewModel
import com.example.synema.view.components.BottomBar
import com.example.synema.view.components.InlineIcon
import com.example.synema.view.components.MainContainer
import com.example.synema.view.components.TopBar

@SuppressLint("UnrememberedMutableState")
@Composable
fun WriteReviewScreen(
    navController: NavHostController,
    profileState: MutableState<ProfileModel>,
    movieID: String?
) {
    val reviewText = mutableStateOf("")
    val rating = mutableStateOf(0)


    var movie: MovieModel by remember {
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
        movieDataSource.loadMovie(movieID) {
            movie = it.getResult()!!
        }
    }

    var reviewList: List<ReviewModel> by remember {
        mutableStateOf(listOf())
    }

    movieDataSource.getReviewsForMovie(movieID.toString(), profileState.value.token) {
        if (it.successful()) {
            it.getResult()?.let { reviewModel ->
                reviewList = reviewModel
                reviewList.forEach { review ->
                    if (review.userid == profileState.value.id) {
                        reviewText.value = review.reviewText
                        rating.value = review.rating
                    }
                }
            }
        }
    }

    GradientBox {
        Column {
            TopBar(
                title = "Review ${movie.title}",
                alignment = Alignment.Center,
                backArrow = true,
                navController = navController,
                fontSize = 15.sp
            )
            MainContainer(hasBottomNav = true) {

                Box(
                    modifier = Modifier
                        .padding(15.dp)
                        .fillMaxWidth()
                        .height(75.dp)
                        .background(color = Color(0xFF75146B), shape = RoundedCornerShape(4.dp))
                ) {
                    RatingStars(rating)
                }

                ReviewBox(reviewText)

                Spacer(modifier = Modifier.height(5.dp))

                Box(modifier = Modifier.fillMaxSize()) {
                    Row(
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Button(
                            onClick = {
                                movieDataSource.delete_review(
                                    movieID.toString(),
                                    profileState.value.token,
                                    profileState.value
                                ) {
                                    navController.popBackStack()
                                }
                            },
                            shape = RoundedCornerShape(20),
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFB13563)),
                            contentPadding = PaddingValues(horizontal = 15.dp),
                            modifier = Modifier.size(width = 150.dp, height = 50.dp)
                        ) {
                            Text(text = "Delete", fontSize = 20.sp)
                        }
                        Button(
                            onClick = {
                                movieDataSource.createReviewForMovie(
                                    movieID.toString(),
                                    reviewText.value,
                                    rating.value,
                                    profileState.value.token,
                                    profileState.value
                                ) {
                                    navController.popBackStack()
                                }
                            },
                            shape = RoundedCornerShape(20),
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF912BB1)),
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
fun ReviewBox(reviewText: MutableState<String>) {
    val maxChar = 1000
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(400.dp)
            .padding(horizontal = 15.dp, vertical = 10.dp)
            .background(Color.White, shape = RoundedCornerShape(4.dp)),
    )
    {
        OutlinedTextField(
            value = reviewText.value,
            onValueChange = { if (it.length <= maxChar) reviewText.value = it.take(maxChar) },
            label = { Text("Review") },
            modifier = Modifier
                .padding(horizontal = 10.dp, vertical = 10.dp)
                .fillMaxSize(),
            supportingText = {
                Text(
                    text = "${reviewText.value.length} / $maxChar",
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.End,
                )
            }
        )
    }
}

@Composable
private fun RatingStars(rating: MutableState<Int>) {
    val size = 63.dp
    val spacing = 0.dp

    Row(horizontalArrangement = Arrangement.SpaceEvenly) {
        Button(
            onClick = { rating.value = 1 }, shape = RectangleShape,
            contentPadding = PaddingValues(horizontal = 5.dp),
            border = BorderStroke(0.dp, Color.Transparent),
            colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.Transparent),
            modifier = Modifier.fillMaxHeight()
        ) {
            if (rating.value > 0) {
                InlineIcon(
                    resourceID = R.drawable.icon_star,
                    size = size,
                    spacing = spacing,
                    tint = Color(0xFF4399FF)
                )
            } else {
                InlineIcon(resourceID = R.drawable.whitestar, size = size, spacing = spacing)
            }
        }
        Button(
            onClick = { rating.value = 2 }, shape = RectangleShape,
            border = BorderStroke(0.dp, Color.Transparent),
            contentPadding = PaddingValues(horizontal = 5.dp),
            colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.Transparent),
            modifier = Modifier.fillMaxHeight()
        ) {
            if (rating.value > 1) {
                InlineIcon(
                    resourceID = R.drawable.icon_star,
                    size = size,
                    spacing = spacing,
                    tint = Color(0xFF4399FF)
                )
            } else {
                InlineIcon(resourceID = R.drawable.whitestar, size = size, spacing = spacing)
            }
        }
        Button(
            onClick = { rating.value = 3 }, shape = RectangleShape,
            border = BorderStroke(0.dp, Color.Transparent),
            contentPadding = PaddingValues(horizontal = 5.dp),
            colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.Transparent),
            modifier = Modifier.fillMaxHeight()
        ) {
            if (rating.value > 2) {
                InlineIcon(
                    resourceID = R.drawable.icon_star,
                    size = size,
                    spacing = spacing,
                    tint = Color(0xFF4399FF)
                )
            } else {
                InlineIcon(resourceID = R.drawable.whitestar, size = size, spacing = spacing)
            }
        }
        Button(
            onClick = { rating.value = 4 }, shape = RectangleShape,
            border = BorderStroke(0.dp, Color.Transparent),
            contentPadding = PaddingValues(horizontal = 5.dp),
            colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.Transparent),
            modifier = Modifier.fillMaxHeight()
        ) {
            if (rating.value > 3) {
                InlineIcon(
                    resourceID = R.drawable.icon_star,
                    size = size,
                    spacing = spacing,
                    tint = Color(0xFF4399FF)
                )
            } else {
                InlineIcon(resourceID = R.drawable.whitestar, size = size, spacing = spacing)
            }
        }
        Button(
            onClick = { rating.value = 5 }, shape = RectangleShape,
            border = BorderStroke(0.dp, Color.Transparent),
            contentPadding = PaddingValues(horizontal = 5.dp),
            colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.Transparent),
            modifier = Modifier.fillMaxHeight()
        ) {
            if (rating.value > 4) {
                InlineIcon(
                    resourceID = R.drawable.icon_star,
                    size = size,
                    spacing = spacing,
                    tint = Color(0xFF4399FF)
                )
            } else {
                InlineIcon(resourceID = R.drawable.whitestar, size = size, spacing = spacing)
            }
        }
    }
}

