package com.example.synema.view.screens

import GradientBox
import MoviePosterFrame
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.synema.R
import com.example.synema.controller.AppContext
import com.example.synema.model.ProfileModel
import com.example.synema.view.components.BottomBar
import com.example.synema.view.components.MainContainer
import com.example.synema.view.components.OpaqueButton
import com.example.synema.view.components.SynemaLogo
import com.example.synema.view.components.TopBar
import com.example.synema.viewmodel.ProfileViewModel


@Composable
fun OUprofiles(navController : NavHostController, profileState: MutableState<ProfileModel>) {
    val context = AppContext.getInstance();
    GradientBox(){

        Column {
            TopBar(title = "Other User", Alignment.Center)
            MainContainer(hasBottomNav = true){
                EditProfileButton()
                ProfileNameHeader("Devon Jeffery")
                ProfilePicture()
                FollowersReviewsStatus(7522, 955)
                PersonalDescription()
                Directories(context.getNav())

            }
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

    Row (modifier = Modifier
        .fillMaxWidth()
        .padding(10.dp), horizontalArrangement = Arrangement.Center){
        Image(
            painter = painterResource(R.drawable.devon),
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
    var c = LocalContext.current;
    var profileViewModel : ProfileViewModel = viewModel()
    Row(horizontalArrangement = Arrangement.End, modifier = Modifier
        .padding(10.dp)
        .fillMaxWidth()){
        OpaqueButton("Edit profile", onClick = {
            profileViewModel.logout(c);
        },
            modifier= Modifier.defaultMinSize(minHeight = 8.dp) )
    }
}


@Composable
private fun PersonalDescription() {

    Box(
        modifier = Modifier
            .fillMaxSize()


    ) {
        Box(
            modifier = Modifier
                .width(334.dp)
                .height(127.dp)
                .background(Color(0xFF543b5b), shape = RoundedCornerShape(10.dp))
                .align(Alignment.Center)

        ) {
            Text(
                "subscribe for a full scale analysis of your favorite movies and the best ratings from me innit, gang gang!",
                color = Color(0xFFC0AEDC),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, top = 16.dp)

            )
        }
    }

}


@Composable
private fun DirectoryCard(text : String, navController: NavHostController, route: String = "") {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .background(Color(0xFFB15FA8).copy(alpha = 0.3F), shape = RoundedCornerShape(10.dp))
            .padding(2.dp)
            .clickable { navController.navigate(route) }



    ) {
        Text(
            text = text,
            color = Color(0xFFD9D9D9),
            fontSize = 17.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp)

        )

        Image(
            painter = painterResource(id = R.drawable.arrow_right),
            contentDescription = null,
            modifier = Modifier
                .size(35.dp)
                .padding(8.dp)
                .align(Alignment.CenterEnd)

        )
    }
}

@Composable
private fun Directories(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {

        // Button(onClick = { navController.navigate("watchlists") }, shape = RoundedCornerShape(10) ) {

        DirectoryCard("Watchlist", navController = navController, route = "watchlists")
        Spacer(modifier = Modifier.height(8.dp))
        //}
        DirectoryCard("Followers", navController = navController, route = "home")
        Spacer(modifier = Modifier.height(8.dp))

        DirectoryCard("Reviews", navController = navController, route = "myreviews")

    }

}
