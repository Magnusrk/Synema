package com.example.synema.view.screens
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.synema.view.components.MovieClip
import com.example.synema.view.components.TitleFont
import androidx.compose.foundation.layout.Spacer
import com.example.synema.R
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.Icon
import android.util.Range
import androidx.annotation.DrawableRes
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.synema.Data.Datasource
import com.example.synema.model.MovieModel
import com.example.synema.model.ProfileModel
import com.example.synema.model.ReviewModel
import com.example.synema.view.components.InlineIcon
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


    val testMovie : MovieModel = Datasource().loadMovie()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState()),
    ) {
            TopBar("", Alignment.CenterStart, 20.sp, backArrow = true, navController = navController)
            TitleFont()
            MovieClip(testMovie.imageResourceId)
            InteractionPane(testMovie)
            DescriptionSection()
            UserReviewSection(Datasource().loadReviews())
        }


}


@Composable
fun InteractionPane(movie : MovieModel){
    val size = Size();
    Row(modifier = Modifier
        .fillMaxWidth()
        .height((size.height() / 7).dp)){
        SaveButton()
        RatingPanel(movie)
    }
}

@Composable
fun SaveButton(){
    val size = Size();
    Column(
        modifier = Modifier
            .width((size.width() / 2).dp)
            .padding(horizontal = 15.dp)
            .fillMaxHeight()
            ,
        verticalArrangement = Arrangement.Center

    ) {
        Button( onClick = {},
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
fun RatingPanel(movie : MovieModel){
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
        Button( onClick = {},
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
fun RatingStars(rating : Float){
    Row ( horizontalArrangement = Arrangement.SpaceEvenly){
        for( n  in 1..5){
            if(rating >= n.toFloat()){
                InlineIcon(resourceID = R.drawable.icon_star, size = 20.dp, spacing = 2.dp, tint= Color(0xFF4399FF))
            } else{
                InlineIcon(resourceID = R.drawable.icon_star, size = 20.dp, spacing = 2.dp)
            }


        }
    }
}



@Composable
fun DescriptionSection(){

    val desc = """
        "Interstellar" is a mind-bending sci-fi epic directed by Christopher Nolan.
        In a desperate bid to save humanity,
        a group of astronauts embarks on a perilous journey through a wormhole to find a new habitable planet.
        It's a visually stunning and emotionally charged exploration of love, time dilation,
        and the resilience of the human spirit.
    """.trimIndent()


    Text("Description", fontWeight = FontWeight.Bold, modifier = Modifier.padding(bottom =10.dp, start=20.dp))
    Box(modifier = Modifier
        .fillMaxWidth()
        .height(1.dp)
        .background(color = Color.Black))
    Text(desc, modifier = Modifier.padding(bottom =10.dp, start=20.dp))

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
            Text(modifier = Modifier.height(30.dp), text= review.owner.name, fontWeight = FontWeight.Bold, color = Color.White, overflow = TextOverflow.Ellipsis)
            RatingStars(review.rating.toFloat())
        }

        Text(review.reviewText,  color = Color.White, overflow = TextOverflow.Ellipsis, maxLines = 3)
        Column (horizontalAlignment = Alignment.End, verticalArrangement = Arrangement.Bottom, modifier = Modifier.fillMaxWidth()){
            OpaqueButton(label = "More", onClick = { }, Modifier.defaultMinSize(minHeight = 5.dp))
        }

    }

}







