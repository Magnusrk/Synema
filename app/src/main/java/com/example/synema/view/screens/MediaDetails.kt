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
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow

import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.synema.Data.Datasource
import com.example.synema.model.MovieModel
import com.example.synema.model.ProfileModel
import com.example.synema.model.ReviewModel
import com.example.synema.view.components.OpaqueButton
import com.example.synema.view.components.TopBar
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment


@Composable
fun InteractionPane(){
    val size = Size();
    Row(modifier = Modifier
        .fillMaxWidth()
        .height((size.height() / 7).dp)){
        SaveButton()
        RatingPanel()
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
        ){
            Text("Save")
        }
    }

}

@Composable
fun RatingPanel(){
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
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF430B3D))
            ){
            Text("Stars")
        }
        Button( onClick = {},
            shape = RoundedCornerShape(20),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF430B3D))
            ){
            Text("Review")
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
        Text(modifier = Modifier.height(30.dp), text= review.owner.name, fontWeight = FontWeight.Bold, color = Color.White, overflow = TextOverflow.Ellipsis)
        Text(review.reviewText,  color = Color.White, overflow = TextOverflow.Ellipsis, maxLines = 3)
        Column (horizontalAlignment = Alignment.End, verticalArrangement = Arrangement.Bottom, modifier = Modifier.fillMaxWidth()){
            OpaqueButton(label = "More", onClick = { }, Modifier.defaultMinSize(minHeight = 5.dp))
        }

    }

}

@Composable
fun MediaDetails(
    navController: NavHostController,
    profileState: MutableState<ProfileModel>,
    movieID: String?
) {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState()),
    ) {
        TopBar("", Alignment.CenterStart, 20.sp, backArrow = true, navController = navController)
        TitleFont()
        val bitmap: Bitmap = BitmapFactory.decodeResource(context.resources, R.drawable.intersteller)
        val imageBitmap: ImageBitmap = bitmap.asImageBitmap()
        MovieClip(bitmap = imageBitmap)
        InteractionPane()
        DescriptionSection()
        UserReviewSection(Datasource().loadReviews())
    }
}

class Size {
    @Composable
    fun height(): Int {
        val configuration = LocalConfiguration.current
        return configuration.screenHeightDp
    }
    @Composable
    fun width(): Int {
        val configuration = LocalConfiguration.current
        return configuration.screenWidthDp
    }
}



