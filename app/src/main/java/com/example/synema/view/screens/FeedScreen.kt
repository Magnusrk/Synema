package com.example.synema.view.screens

import GradientBox
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.synema.Data.DependencyProvider
import com.example.synema.R
import com.example.synema.model.MovieModel
import com.example.synema.model.ProfileModel
import com.example.synema.model.ReviewModel
import com.example.synema.ui.theme.SynemaTheme
import com.example.synema.view.components.BottomBar
import com.example.synema.view.components.InlineIcon
import com.example.synema.view.components.LoadingWrapper
import com.example.synema.view.components.MainContainer
import com.example.synema.view.components.MovieClip
import com.example.synema.view.components.OpaqueButton
import com.example.synema.view.components.TitleFont
import com.example.synema.view.components.TopBar
import com.example.synema.viewmodel.FeedViewModel
import com.example.synema.viewmodel.FollowersViewModel
import com.example.synema.viewmodel.ProfileViewModel
import com.example.synema.viewmodel.media.MediaDetailsViewModel
import org.junit.Assert.assertEquals
import java.util.Date

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Feed(navController: NavHostController, profileState: MutableState<ProfileModel>) {

    val vm: FeedViewModel = viewModel()
    vm.init(profileState.value)

    SynemaTheme {
        GradientBox {
            Column {
                TopBar(
                    "Feed",
                    Alignment.Center,
                    20.sp,
                    backArrow = false,
                    transparent = true,
                    search = false,
                    textInput = false,
                    navController = navController,
                    onChange = {
                    },
                )

                MainContainer(hasBottomNav = true, scrollAble = true) {
                    LoadingWrapper(vm.isLoading) {
                        if (!vm.reviewList.isEmpty()) {
                            UserReviewSection(vm)
                        }
                    }

                }
                BottomBar(navController = navController)
            }
        }
    }
}

@Composable
private fun UserReviewSection(vm: FeedViewModel) {

    Text(
        "User reviews",
        fontWeight = FontWeight.Bold,
        modifier = Modifier.padding(bottom = 10.dp, start = 20.dp),
        color = Color.White
    )
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(1.dp)
            .background(color = Color(0xFFB6842D))
    )

    Column() {
        vm.reviewList.forEach() { review -> UserReviewCard(review, vm) }
    }

}

@Composable
private fun UserReviewCard(review: ReviewModel, vm: FeedViewModel) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .defaultMinSize(70.dp)
            .padding(horizontal = 15.dp, vertical = 10.dp)
    )
    {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color(0xFF430B3D), shape = RoundedCornerShape(10.dp))

        ) {
            InnerReviewContainer(review, vm)
        }

    }
}

@Composable
private fun InnerReviewContainer(review: ReviewModel, vm: FeedViewModel) {
    var expanded by remember { mutableStateOf(false) }
    var moreText by remember {
        mutableStateOf("More")
    }


    val source = DependencyProvider.getInstance().getMovieSource();
    var movie: MovieModel by remember {
        mutableStateOf(
            MovieModel(
                0,
                "",
                "",
                "Loading...",
                "Loading...",
                0,
                "",
                ""
            )
        )
    }

    if (review.movieid != null) {
        source.loadMovie(review.movieid) {
            movie = it.getResult()!!
        }
    }

    Column(
        modifier = Modifier.padding(10.dp)
    ) {
        Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
            ClickableText(
                text = AnnotatedString("@" + review.username),
                style = TextStyle(
                    color = Color.White,
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Bold,
                    textDecoration = TextDecoration.Underline
                ),
                onClick = {
                    vm.getNav().navigate("ouprofiles/" + review.userid)
                })

            ReviewStars(review.rating * 2)
        }

        ClickableText(
            text = AnnotatedString(movie.title),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            style = TextStyle(
                color = Color.White,
                fontWeight = FontWeight.Bold,
                textDecoration = TextDecoration.Underline,
            ),
            onClick = {
                vm.getNav().navigate("mediaDetails/" + review.movieid)
            })
        
        Spacer(modifier = Modifier.size(5.dp))


        if (expanded) {
            Text(
                review.reviewText,
                color = Color.White,
                overflow = TextOverflow.Ellipsis,
            )
            Spacer(modifier = Modifier.size(5.dp))
            Text(text = review.date,
                color = Color.White,
                style = TextStyle(
                    fontStyle = FontStyle.Italic
                )
            )

        } else {
            Text(
                review.reviewText,
                color = Color.White,
                overflow = TextOverflow.Ellipsis,
                maxLines = 3
            )
            Spacer(modifier = Modifier.size(5.dp))
            Text(text = review.date,
                color = Color.White,
                style = TextStyle(
                    fontStyle = FontStyle.Italic
                )
            )
        }
        Column(
            horizontalAlignment = Alignment.End,
            verticalArrangement = Arrangement.Bottom,
            modifier = Modifier.fillMaxWidth()
        ) {
            if (review.reviewText.length > 30) {
                OpaqueButton(
                    label = moreText,
                    onClick = {
                        expanded = !expanded;
                        if (expanded) {
                            moreText = "Less"
                        } else {
                            moreText = "More"
                        }
                    },
                    Modifier.defaultMinSize(minHeight = 5.dp)
                )
            }
        }

    }
}

@Composable
private fun ReviewStars(rating: Number) {
    Row(horizontalArrangement = Arrangement.SpaceEvenly) {
        for (n in 1..5) {
            if (rating.toFloat() / 2 >= n.toFloat()) {
                InlineIcon(
                    resourceID = R.drawable.icon_star, size = 20.dp, spacing = 2.dp, tint = Color(
                        0xFFB6842D
                    )
                )
            } else {
                InlineIcon(resourceID = R.drawable.icon_star, size = 20.dp, spacing = 2.dp)
            }
        }
    }
}