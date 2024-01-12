package com.example.synema.view.screens

import GradientBox
import androidx.compose.foundation.BorderStroke
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
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
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.synema.Data.DependencyProvider
import com.example.synema.R
import com.example.synema.controller.AppContext
import com.example.synema.model.MovieModel
import com.example.synema.model.ProfileModel
import com.example.synema.model.ReviewModel
import com.example.synema.view.components.BottomBar
import com.example.synema.view.components.InlineIcon
import com.example.synema.view.components.MainContainer
import com.example.synema.view.components.OpaqueButton
import com.example.synema.view.components.TopBar

@Composable
fun MyReviews(navController: NavHostController, profileState: MutableState<ProfileModel>) {

    val source = DependencyProvider.getInstance().getMovieSource();
    var reviewList : List<ReviewModel> by remember {
        mutableStateOf(listOf())
    }

    source.getOwnReviews(profileState.value.token){
        if (it.successful()) {
            it.getResult()?.let {reviewModel ->
                reviewList = reviewModel
            }
        }
    }

    GradientBox {
        Column {
            TopBar(
                title = "My Reviews",
                alignment = Alignment.Center,
                backArrow = true,
                navController = navController,

                )
            MainContainer(hasBottomNav = true) {
                myReviewSection(reviewList)

                Spacer(modifier = Modifier.height(5.dp))

                Box(modifier = Modifier.fillMaxSize()) {
                    Row(
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        modifier = Modifier.fillMaxWidth()
                    ) {

                    }

                }
            }
            BottomBar(navController = navController)

        }
    }
}

@Composable
private fun UserReviewCard(review : ReviewModel){
    Box(modifier= Modifier
        .fillMaxWidth()
        .defaultMinSize(70.dp)
        .padding(horizontal = 15.dp, vertical = 10.dp)
    )
    {
        Box(modifier= Modifier
            .fillMaxSize()
            .background(color = Color(0xFF430B3D), shape = RoundedCornerShape(10.dp))

        ){
            InnerReviewContainer(review)
        }

    }

}
@Composable
fun myReviewSection(reviewList: List<ReviewModel>){
    Column(){
        reviewList.forEach(){review -> UserReviewCard(review)}
    }

}

@Composable
private fun InnerReviewContainer(review : ReviewModel){
    val source = DependencyProvider.getInstance().getMovieSource();
    var movie : MovieModel by remember {
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
        source.loadMovie(review.movieid){
            movie = it.getResult()!!
        }
    }
    var expanded by remember { mutableStateOf (false) }
    var moreText by remember {
        mutableStateOf("More")
    }

    Column(
        modifier = Modifier.padding(10.dp)
    ) {
        Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()){
            Text(modifier = Modifier.height(30.dp).width(200.dp), text=movie.title, fontWeight = FontWeight.Bold, color = Color.White, overflow = TextOverflow.Ellipsis)
            myRatingStars(review.rating*2)
        }

        if (expanded) {
            Text(
                review.reviewText,
                color = Color.White,
                overflow = TextOverflow.Ellipsis,
            )
        } else{
            Text(
                review.reviewText,
                color = Color.White,
                overflow = TextOverflow.Ellipsis,
                maxLines = 3
            )
        }
        Column (horizontalAlignment = Alignment.End, verticalArrangement = Arrangement.Bottom, modifier = Modifier.fillMaxWidth()){
            if (review.reviewText.length > 30) {
                OpaqueButton(
                    label = moreText,
                    onClick = { expanded = !expanded;
                        if (expanded){
                            moreText = "Less"
                        } else {
                            moreText = "More"
                        }},
                    Modifier.defaultMinSize(minHeight = 5.dp)
                )
            }
        }

    }

}


@Composable
private fun myRatingStars(rating : Number){
    Row ( horizontalArrangement = Arrangement.SpaceEvenly){
        for( n  in 1..5){
            if(rating.toFloat()/2 >= n.toFloat()){
                InlineIcon(resourceID = R.drawable.icon_star, size = 20.dp, spacing = 2.dp, tint= Color(0xFF4399FF))
            } else{
                InlineIcon(resourceID = R.drawable.icon_star, size = 20.dp, spacing = 2.dp)
            }
        }
    }
}
