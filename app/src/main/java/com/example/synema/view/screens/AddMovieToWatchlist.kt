package com.example.synema.view.screens

import GradientBox
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.synema.model.ProfileModel
import com.example.synema.view.components.BottomBar
import com.example.synema.view.components.MainContainer
import com.example.synema.view.components.TopBar
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.synema.Data.DependencyProvider
import com.example.synema.R
import com.example.synema.model.WatchlistModel
import com.example.synema.model.MovieModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddMovieToWatchlist(navController : NavHostController, profileState: MutableState<ProfileModel>, movieID: String?) {
    val watchlistDataSource = DependencyProvider.getInstance().getWatchlistSource();
    var movie : MovieModel by remember {
        mutableStateOf(MovieModel(
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
    val movieDataSource = DependencyProvider.getInstance().getMovieSource();

    if (movieID != null) {
        movieDataSource.loadMovie(movieID){
            movie = it.getResult()!!
        }
    }

    GradientBox(){
        Column {

            var watchlistList : List<WatchlistModel> by remember {
                mutableStateOf(listOf())
            }

            watchlistDataSource.read_db (profileState.value.token){
                if (it.successful()) {
                    it.getResult()?.let {watchlistModel ->
                        watchlistList = watchlistModel
                    }
                }

            }
            TopBar(title = "Add ${movie.title}", alignment = Alignment.Center, backArrow = true, navController = navController, fontSize = 15.sp)
            MainContainer(hasBottomNav = true){

                watchlistList(watchlistList = watchlistList, header ="" , navController = navController, movie = movie, profileState = profileState)

            }
            BottomBar(navController = navController)
        }

    }
}


@Composable
private fun watchlistList(watchlistList: List<WatchlistModel>, modifier: Modifier = Modifier, header: String, navController : NavHostController, movie : MovieModel, profileState: MutableState<ProfileModel>) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 34.dp)

    ) {
        Text(
            text = header,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            modifier = Modifier
                .padding(8.dp)
        )
        Column(modifier = modifier) {
            watchlistList.forEach(){
                watchlist -> watchlistCard(watchlist = watchlist, navController =navController, movie=movie, profileState = profileState)

/*
            items(watchlistList) { watchlist ->
            watchlistCard(
                    watchlist = watchlist,
                    modifier = Modifier.padding(8.dp),
                    navController
                )*/

                
            }
            if(watchlistList.isEmpty()){
                Text(text="You have no watchlist", color = Color.White, fontSize = 15.sp)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun watchlistCard(watchlist: WatchlistModel, modifier: Modifier = Modifier, navController : NavHostController, movie: MovieModel, profileState: MutableState<ProfileModel>) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 5.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(100.dp, 100.dp)
                    .background(color = Color(0xFFB15FA8), shape = RoundedCornerShape(4.dp))
                    .clickable(onClick = {
                        val watchlistDataSource = DependencyProvider.getInstance().getWatchlistSource()
                        watchlistDataSource.addMovieToWatchlist(watchlist.watchlist_id, movie.id.toString(), profileState.value.token){
                            navController.popBackStack()
                        }
                    }).padding(2.dp)

            ) {
                ImageCardRow(watchlist.icons)
            }
            Text(
                text = watchlist.name,
                fontSize = 20.sp,
                overflow = TextOverflow.Ellipsis,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier
                    .size(200.dp, 98.dp)
                    .padding(10.dp)
            )

        }
    }

@Composable
private fun ImageCardRow(movieUrls: List<String>) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        //verticalArrangement = Arrangement.SpaceEvenly,
        // horizontalAlignment = Alignment.CenterHorizontally

    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(2F),
            //horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            ImageCard(movieUrls[0], modifier = Modifier.weight(2F))
            ImageCard(movieUrls[1], modifier = Modifier.weight(2F))
        }

        //Spacer(modifier = Modifier.height(2.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(2F),
            horizontalArrangement = Arrangement.SpaceEvenly,

            ) {
            ImageCard(
                movieUrls[2], modifier = Modifier
                    .fillMaxSize()
                //.padding(bottom = 2.dp)
            )
            ImageCard(
                movieUrls[3], modifier = Modifier
                    .fillMaxSize()
                //.padding(bottom = 2.dp)
            )
        }
    }
}

@Composable
private fun ImageCard(imageUrl: String, modifier: Modifier = Modifier) {
    Box(
        modifier = Modifier
            .size(50.dp)
            .background(Color(0xFFB15FA8), /*shape = RoundedCornerShape(4.dp)*/)
    ) {
        if (imageUrl != "https://i0.wp.com/godstedlund.dk/wp-content/uploads/2023/04/placeholder-5.png?w=1200&ssl=1") {
            AsyncImage(
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize(),
                //.clip(RoundedCornerShape(4.dp)),
                contentScale = ContentScale.Crop,
                model = imageUrl
            )
        }
    }

}