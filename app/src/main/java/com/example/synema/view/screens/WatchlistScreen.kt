package com.example.synema.view.screens

import GradientBox
import MoviePosterFrame
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.synema.model.ProfileModel
import com.example.synema.view.components.BottomBar
import com.example.synema.view.components.MainContainer
import com.example.synema.view.components.SynemaLogo
import com.example.synema.view.components.TopBar
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.TextField
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.sp
import com.example.synema.Data.DependencyProvider
import com.example.synema.R
import com.example.synema.model.WatchlistModel
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupProperties
import coil.compose.AsyncImage
import com.example.synema.view.components.OpaqueButton


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WatchList(navController : NavHostController, profileState: MutableState<ProfileModel>) {
    val dataSource = DependencyProvider.getInstance().getWatchlistSource();

    GradientBox(){
        Column {

            var watchlistList : List<WatchlistModel> by remember {
                mutableStateOf(listOf())
            }
            val popupControl = remember { mutableStateOf(false) }
            val watchlistName = remember { mutableStateOf("")}

            dataSource.read_db (profileState.value.token){
                if (it.successful()) {
                    it.getResult()?.let {watchlistModel ->
                        watchlistList = watchlistModel
                    }
                }

            }
            MainContainer(hasBottomNav = true){
                TopBar(title = "My Watchlists", alignment = Alignment.Center)


                CreateWatchlistPopup(popupControl, watchlistName, navController, profileState);
                newWatchlist(popupControl)
                wathclistList(watchlistList = watchlistList, header ="" , navController = navController )

            };
            BottomBar(navController = navController)
        }

    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CreateWatchlistPopup(openDialog : MutableState<Boolean>, watchlistName : MutableState<String>, navController: NavHostController, profileState: MutableState<ProfileModel>){



    if (openDialog.value) {
        Popup(
            // on below line we are adding
            // alignment and properties.
            onDismissRequest = { openDialog.value = false },
            properties = PopupProperties(focusable = true),
            alignment = Alignment.TopCenter,

            ) {

            // on the below line we are creating a box.
            Column(

                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(top=250.dp)
                // on below line we are adding border.

            ) {
                PopUpHeader(openDialog = openDialog, navController = navController)
                WatchlistCreationPane(openDialog = openDialog, watchlistName = watchlistName, navController = navController)
                Row(modifier = Modifier
                    .height(50.dp)
                    .fillMaxWidth()
                    .background(Color(0xFF430B3D)),
                    horizontalArrangement = Arrangement.Center

                ){
                    // Button to create watchlist
                    Button( onClick = {
                        val dataSource = DependencyProvider.getInstance().getWatchlistSource();
                        // Call your createWatchlist function here
                        dataSource.createWatchlist(watchlistName.value, profileState.value.token){}
                        openDialog.value = false
                        navController.currentDestination?.let { navController.navigate(it.id) }
                        // You might want to reset the watchlistName after creating a watchlist
                        //watchlistName = ""

                    },
                        shape = RoundedCornerShape(20),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF811C77)),
                        contentPadding = PaddingValues(horizontal = 20.dp)
                    ){
                        Row {
                            Text("Done")
                        }

                    }
                }


            }
        }
    }


}


@Composable
private fun PopUpHeader(openDialog: MutableState<Boolean>, navController: NavHostController){
    Row (modifier = Modifier
        .fillMaxWidth()
        .height(56.dp)
        .background(color = Color(0xFF63105B))
        ,
        horizontalArrangement = Arrangement.SpaceBetween
    ){
        Row (){
            Column(modifier = Modifier.align(Alignment.CenterVertically)) {
                OpaqueButton(label = "Cancel", onClick = { openDialog.value = false}, modifier = Modifier.align(Alignment.Start))
            }
            Column(modifier = Modifier
                .align(Alignment.CenterVertically)
                .fillMaxWidth()) {
                Text("Create watchlist", color = Color.White, modifier = Modifier.align(Alignment.CenterHorizontally), fontSize = 20.sp)
            }


        }







    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun WatchlistCreationPane(openDialog : MutableState<Boolean>, watchlistName: MutableState<String>, navController: NavHostController){

    Row(modifier = Modifier
        .height(100.dp)
        .background(Color(0xFF430B3D))){
        Row(){
            TextField(
                value = watchlistName.value,
                onValueChange = { watchlistName.value = it },
                label = { Text("Watchlist Name") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun newWatchlist(openDialog : MutableState<Boolean>){
    val dataSource = DependencyProvider.getInstance().getWatchlistSource();

    Row(modifier = Modifier
        .fillMaxWidth()
        .height(100.dp)
        .offset(34.dp, 33.dp)
        .verticalScroll(rememberScrollState())
    ){

        Box(modifier = Modifier
            .height(100.dp)
            .width(100.dp)
            .background(Color(0xFFB15FA8))
            .clickable {
                openDialog.value = true;

            }
        )

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

@Composable
fun wathclistList(watchlistList: List<WatchlistModel>, modifier: Modifier = Modifier, header: String, navController : NavHostController) {

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
                    watchlist -> watchlistCard(watchlist = watchlist, navController =navController )

                /*
                            items(watchlistList) { watchlist ->
                            watchlistCard(
                                    watchlist = watchlist,
                                    modifier = Modifier.padding(8.dp),
                                    navController
                                )*/


            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun watchlistCard(watchlist: WatchlistModel, modifier: Modifier = Modifier, navController : NavHostController) {
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
                .clickable(onClick = { navController.popBackStack() })
                .padding(2.dp)

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

        Surface(
            modifier = Modifier.size(30.dp),
            color = Color(0, 0, 0, 0),
            onClick = {}) {
            Image(
                painter = painterResource(id = R.drawable.edit_playlist),
                contentDescription = null,
                modifier = Modifier.size(30.dp)
            )
        }
    }
}

@Composable
fun ImageCard(imageUrl: String, modifier: Modifier = Modifier) {
    Box(
        modifier = Modifier
            .size(45.dp)
            .background(Color(0xFFB15FA8), shape = RoundedCornerShape(4.dp))
    ){
        AsyncImage(
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(4.dp)),
            contentScale = ContentScale.Crop,
            model = imageUrl
        )
    }
}
@Composable
fun ImageCardRow(movieUrls : List<String>) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(2F),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            ImageCard(movieUrls[0], modifier = Modifier.
            weight(2F))
            ImageCard(movieUrls[1], modifier = Modifier
                .weight(2F))
        }

        Spacer(modifier = Modifier.height(2.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(2F),
            horizontalArrangement = Arrangement.SpaceEvenly,

            ){
            ImageCard(movieUrls[2], modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 2.dp))
            ImageCard (movieUrls[3], modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 2.dp))
        }
    }
}

/*
Surface(
    modifier = modifier,
    shape = RoundedCornerShape(10.dp), // Customize the shape if needed
    color = Color(0x00000000) // Set the color to transparent
) {
    Column(
        //verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.width(95.dp)

    ) {
        Image(painter = painterResource(id = R.drawable.actual_plus_symbol), contentDescription = null,
            modifier = Modifier
                .width(100.dp)
                .height(100.dp))
    }
     AsyncImage(
            model = movie.poster_url,
            contentDescription = null,
            modifier = Modifier
                .width(95.dp)
                .height(135.dp)
                .clickable { navController.navigate("mediaDetails/" + movie.id) }
            ,
            contentScale = ContentScale.FillBounds
        )


    Spacer(modifier = Modifier.height(5.dp))
    Text(
        text = watchlist.name,
        fontSize = 12.sp,
        fontWeight = FontWeight.Bold,
        color = Color.White,
        overflow = TextOverflow.Ellipsis,
        maxLines = 2,
        lineHeight = 12.sp,
        textAlign = TextAlign.Center

    )
}

}
*/ /* */