package com.example.synema.view.screens

import GradientBox
import MoviePosterFrame
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.synema.model.ProfileModel
import com.example.synema.view.components.BottomBar
import com.example.synema.view.components.MainContainer
import com.example.synema.view.components.SynemaLogo
import com.example.synema.view.components.TopBar
import com.example.synema.view.utils.Size
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size


@Composable
fun WatchList(navController : NavHostController, profileState: MutableState<ProfileModel>) {
    GradientBox(){
        Column {
            MainContainer(hasBottomNav = true){
                TopBar(title = "My Watchlists", alignment = Alignment.Center)
                newWatchlist()
            };
            BottomBar(navController = navController)
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
private fun newWatchlist(){
    Row(modifier = Modifier
        .fillMaxWidth()
        .height(100.dp)
        .offset(34.dp, 33.dp)
    ){

        Box(modifier = Modifier
            .height(100.dp)
            .width(100.dp)
            .background(Color(0xFFB15FA8))){
            Column(modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                Box(modifier = Modifier.size(40.dp,4.dp).background(Color(0xFF63105B)))
                Box(modifier = Modifier.size(4.dp,40.dp).background(Color(0xFF63105B)))

            }
        }
        }

    }

