package com.example.synema.view.screens
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.synema.view.components.MovieClip
import com.example.synema.view.components.TitleFont
import com.example.synema.R
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.synema.Data.DependencyProvider
import com.example.synema.Data.movies.MockMovieDataSource
import com.example.synema.model.MovieModel
import com.example.synema.model.ProfileModel
import com.example.synema.model.ReviewModel
import com.example.synema.model.WatchlistModel
import com.example.synema.view.components.InlineIcon
import com.example.synema.view.components.MainContainer
import com.example.synema.view.components.OpaqueButton
import com.example.synema.view.components.TopBar
import com.example.synema.view.utils.Size


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MediaDetails(
    navController: NavHostController,
    profileState: MutableState<ProfileModel>,
    movieID: String?
) {



    val source = DependencyProvider.getInstance().getMovieSource();
    var reviewList : List<ReviewModel> by remember {
        mutableStateOf(listOf())
    }

    source.getReviewsForMovie(movieID.toString(),profileState.value.token){
        if (it.successful()) {
            it.getResult()?.let {reviewModel ->
               reviewList = reviewModel
            }
        }
    }
    var movie : MovieModel by remember {
        mutableStateOf(MovieModel(
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
    if (movieID != null) {
        source.loadMovie(movieID){
            movie = it.getResult()!!
        }
    }


    //val movie : MovieModel = source.loadMovie(movieID.toString())

    MainContainer {
            TopBar("", Alignment.CenterStart, 20.sp, backArrow = true, navController = navController)
            TitleFont(movie.title)
            MovieClip(movie.backdrop_url)
            InteractionPane(movie, navController)
            DescriptionSection(movie.description)
            UserReviewSection(reviewList)
        println(reviewList)
            }
        }





@Composable
fun InteractionPane(movie : MovieModel, navController: NavHostController){
    val size = Size();
    Row(modifier = Modifier
        .fillMaxWidth()
        .height((size.height() / 7).dp)){
        SaveButton(movie, navController = navController)
        RatingPanel(movie, navController = navController)
    }
}

@Composable
fun SaveButton(movie: MovieModel, navController: NavHostController){
    val size = Size();
    Column(
        modifier = Modifier
            .width((size.width() / 2).dp)
            .padding(horizontal = 15.dp)
            .fillMaxHeight()
            ,
        verticalArrangement = Arrangement.Center

    ) {
        Text(text = movie.release_date,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(start = 4.dp, top = 10.dp,bottom = 10.dp))
        Button( onClick = {navController.navigate("mediaDetails/" + movie.id+"/save")},
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4399FF)),
            shape = RoundedCornerShape(20),
            contentPadding = PaddingValues(horizontal = 10.dp)
        ){
            Row(verticalAlignment = Alignment.CenterVertically){
                Text("Save")
                InlineIcon(resourceID = R.drawable.playlist_add)
            }

        }
    }

}


@Composable
fun RatingPanel(movie : MovieModel, navController: NavHostController){
    val size = Size();
    Column(
        modifier = Modifier
            .width((size.width() / 2).dp)
            .padding(horizontal = 15.dp)
            .fillMaxHeight(),
        horizontalAlignment = Alignment.End,
        verticalArrangement = Arrangement.Center

    ) {
        Button( onClick = {},
            shape = RoundedCornerShape(20),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF430B3D)),
            contentPadding = PaddingValues(horizontal = 5.dp)
            ){
            RatingStars(movie.rating)
        }
        Button( onClick = {navController.navigate("mediaDetails/" + movie.id+"/review")},
            shape = RoundedCornerShape(20),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF430B3D)),
            contentPadding = PaddingValues(horizontal = 15.dp)
            ){
            Row {
                Text("Review")
                InlineIcon(resourceID = R.drawable.edit_playlist)
            }

        }
    }

}


@Composable
private fun RatingStars(rating : Number){
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




@Composable
fun DescriptionSection(desc : String){
    Text("Description", fontWeight = FontWeight.Bold, modifier = Modifier.padding(bottom =10.dp, start=20.dp))
    Box(modifier = Modifier
        .fillMaxWidth()
        .height(1.dp)
        .background(color = Color.Black))
    Text(desc, modifier = Modifier.padding(top = 3.dp, bottom =10.dp, start=20.dp))

}

@Composable
fun UserReviewSection(reviewList: List<ReviewModel>){

    Text("User reviews", fontWeight = FontWeight.Bold, modifier = Modifier.padding(bottom =10.dp, start=20.dp))
    Box(modifier = Modifier
        .fillMaxWidth()
        .height(1.dp)
        .background(color = Color.Black))

    Column(){
        reviewList.forEach(){review -> UserReviewCard(review)}
    }

}

@Composable
fun UserReviewCard(review : ReviewModel){
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
fun InnerReviewContainer(review : ReviewModel){
    Column(
        modifier = Modifier.padding(10.dp)
    ) {
        Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()){
            Text(modifier = Modifier.height(30.dp), text= review.username, fontWeight = FontWeight.Bold, color = Color.White, overflow = TextOverflow.Ellipsis)
            RatingStars(review.rating*2)
        }

        Text(review.reviewText,  color = Color.White, overflow = TextOverflow.Ellipsis, maxLines = 3)
        Column (horizontalAlignment = Alignment.End, verticalArrangement = Arrangement.Bottom, modifier = Modifier.fillMaxWidth()){
            OpaqueButton(label = "More", onClick = { }, Modifier.defaultMinSize(minHeight = 5.dp))
        }

    }

}



