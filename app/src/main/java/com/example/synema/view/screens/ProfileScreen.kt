package com.example.synema.view.screens

import GradientBox
import MoviePosterFrame
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.synema.R
import com.example.synema.model.ProfileModel
import com.example.synema.view.components.BottomBar
import com.example.synema.view.components.MainContainer
import com.example.synema.view.components.OpaqueButton
import com.example.synema.view.components.SynemaLogo
import com.example.synema.view.components.TopBar
import com.example.synema.view.utils.Size


@Composable
fun Profile(navController : NavHostController, profileState: MutableState<ProfileModel>) {
    GradientBox(){
        Column {
            MainContainer(hasBottomNav = true){
                TopBar(title = "My Profile", Alignment.Center)
                EditProfileButton()
                ProfileNameHeader(name = profileState.value.name)
                ProfilePicture();
                FollowersReviewsStatus(76, 88);
            };
            BottomBar(navController = navController)
        }

    }
}

@Composable
private fun FollowersReviewsStatus(followers : Int, reviews : Int){
    Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier
        .padding(30.dp)
        .fillMaxWidth()){
        Text("$followers followers", color = Color.White)
        Text("$reviews reviews", color = Color.White)
    }
}
@Composable
private fun ProfilePicture(){

    Row (modifier = Modifier.fillMaxWidth().padding(10.dp), horizontalArrangement = Arrangement.Center){
        Image(
            painter = painterResource(R.drawable.profile_picture_placeholder),
            contentDescription = null,
            modifier = Modifier
                .size(145.dp)
                .clip(CircleShape)
                .border(2.dp, Color.Black, CircleShape)
            ,
            contentScale = ContentScale.Crop
        )
    }

}

@Composable
private fun ProfileNameHeader(name : String){
    Row (modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center){
        Text(text =name, color = Color.White, fontSize = 30.sp, fontWeight = FontWeight.Bold)
    }
}
@Composable
private fun EditProfileButton(){
    Row(horizontalArrangement = Arrangement.End, modifier = Modifier
        .padding(10.dp)
        .fillMaxWidth()){
        OpaqueButton("Edit profile", onClick = {}, modifier= Modifier.defaultMinSize(minHeight = 8.dp) )
    }
}


@Composable
private fun MovieDisplay(){
    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .height(250.dp)
    ){
        MoviePosterFrame(Arrangement.Bottom, "https://static.posters.cz/image/750/plakater/interstellar-ice-walk-i23290.jpg")
        MoviePosterFrame(Arrangement.Center, "https://i.etsystatic.com/10683147/r/il/d4a024/4900691314/il_1080xN.4900691314_fu21.jpg")
        MoviePosterFrame(Arrangement.Top, "https://www.hollywoodreporter.com/wp-content/uploads/2023/06/French-Film-Poster-Barbie-Warner-Bros..jpg?w=999")
    }
}

@Composable
private fun SynHeader() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 77.dp)

    ) {
        SynemaLogo()
    }
}
