package com.example.synema.view.screens

import GradientBox
import MoviePosterFrame
import WatchlistAPISource
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.TextField
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.sp
import com.example.synema.Data.DependencyProvider
import com.example.synema.R
import com.example.synema.controller.WatchlistAPI



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WatchList(navController : NavHostController, profileState: MutableState<ProfileModel>) {
    val dataSource = DependencyProvider.getInstance().getWatchlistSource();

    GradientBox(){
        Column {
            var watchlistName by remember { mutableStateOf("") }

            MainContainer(hasBottomNav = true){
                TopBar(title = "My Watchlists", alignment = Alignment.Center)
                // Input field for watchlist name
                OutlinedTextField(
                    value = watchlistName,
                    onValueChange = { watchlistName = it },
                    label = { Text("Watchlist Name") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                )

                // Button to create watchlist
                Button(
                    onClick = {
                        // Call your createWatchlist function here
                        dataSource.createWatchlist(watchlistName){
                            watchlistName = it.getStatus();
                        }
                        // You might want to reset the watchlistName after creating a watchlist
                        //watchlistName = ""
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Text("Create Watchlist")
                }

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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun newWatchlist(){
    Row(modifier = Modifier
        .fillMaxWidth()
        .height(100.dp)
        .offset(34.dp, 33.dp)
        .verticalScroll(rememberScrollState())
    ){

        Box(modifier = Modifier
            .height(100.dp)
            .width(100.dp)
            .background(Color(0xFFB15FA8)))
        {
            Image(painter = painterResource(id = R.drawable.actual_plus_symbol), contentDescription = null,
                modifier = Modifier
                    .align(Alignment.Center)
                    .width(100.dp)
                    .height(100.dp))
            }
    Text(text = "New Watchlist", fontSize = 16.sp, color =Color.White, modifier = Modifier
        .align(Alignment.CenterVertically)
        .padding(27.dp)
        )
        }

        }



