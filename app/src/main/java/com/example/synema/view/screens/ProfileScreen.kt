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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import coil.compose.AsyncImage
import com.example.synema.Data.DependencyProvider
import com.example.synema.R
import com.example.synema.controller.AppContext
import com.example.synema.model.ProfileModel
import com.example.synema.model.ReviewModel
import com.example.synema.view.components.BottomBar
import com.example.synema.view.components.MainContainer
import com.example.synema.view.components.OpaqueButton
import com.example.synema.view.components.SynemaLogo
import com.example.synema.view.components.TopBar
import com.example.synema.viewmodel.ProfileViewModel
import com.example.synema.viewmodel.media.MediaDetailsViewModel
import okhttp3.internal.userAgent


@Composable
fun Profile(navController : NavHostController, profileState: MutableState<ProfileModel>) {
    val context = AppContext.getInstance();
    println("Pic: " + context.getProfileState().value.profilePicture)

    val source = DependencyProvider.getInstance().getMovieSource();
    var reviewList : List<ReviewModel> by remember {
        mutableStateOf(listOf())
    }

    val vm : ProfileViewModel = viewModel()
    vm.init(profileState.value)


    source.getOwnReviews(profileState.value.token){
        if (it.successful()) {
            it.getResult()?.let {reviewModel ->
                reviewList = reviewModel
            }
        }
    }

    GradientBox(){
        Column {
            TopBar(title = "My Profile", Alignment.Center)
            MainContainer(hasBottomNav = true){
                EditProfileButton()
                ProfileNameHeader(name = context.getProfileState().value.name)
                ProfilePicture(profileState.value)
                FollowersReviewsStatus(vm.followerCount.value, reviewList.size)
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
fun ProfilePicture(currentUser: ProfileModel) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp), horizontalArrangement = Arrangement.Center
    ) {
        println(currentUser.name)
        println(currentUser.profilePicture)
        if (false) {
            Image(
                painter = painterResource(R.drawable.profile_picture_placeholder),
                contentDescription = null,
                modifier = Modifier
                    .size(145.dp)
                    .clip(CircleShape)
                    .border(2.dp, Color.Black, CircleShape),
                contentScale = ContentScale.Crop
            )
        } else {
            println(currentUser.bio)
            AsyncImage(
                model = currentUser.profilePicture, contentDescription = null, modifier = Modifier
                    .size(145.dp)
                    .clip(CircleShape)
                    .border(2.dp, Color.Black, CircleShape),
                contentScale = ContentScale.Crop
            )
        }
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
    val c = LocalContext.current;
    val profileViewModel : ProfileViewModel = viewModel()
    Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier
        .padding(30.dp)
        .fillMaxWidth()){
        OpaqueButton("Edit profile", onClick = {
            profileViewModel.EditProfile()

        },
         modifier= Modifier.defaultMinSize(minHeight = 8.dp) )
        OpaqueButton("Log out",onClick = {
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
                AppContext.getInstance().getProfileState().value.bio,
                color = Color(0xFFC0AEDC),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, top = 16.dp)

            )
        }
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

@Composable
private fun DirectoryCard(text : String, navController: NavHostController,route: String = "") {

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

        DirectoryCard("Reviews", navController = navController, route = "myreviews")
        Spacer(modifier = Modifier.height(8.dp))

        DirectoryCard("Followers", navController = navController, route = "followers/"+AppContext.getInstance().getProfileState().value.id,
        )
        Spacer(modifier = Modifier.height(8.dp))

        DirectoryCard("Following", navController = navController, route = "following/"+AppContext.getInstance().getProfileState().value.id,
        )
        Spacer(modifier = Modifier.height(8.dp))



    }

}

