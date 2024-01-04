package com.example.synema.view.screens

import GradientBox
import MoviePosterFrame
import WatchlistAPISource
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.TextField
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.synema.Data.DependencyProvider
import com.example.synema.R
import com.example.synema.controller.WatchlistAPI
import com.example.synema.model.MovieModel
import com.example.synema.model.WatchlistModel
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupProperties
import okhttp3.internal.wait


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

            dataSource.read_db (){
                if (it.successful()) {
                    it.getResult()?.let {watchlistModel ->
                        watchlistList = watchlistModel
                    }
                }

            }
            MainContainer(hasBottomNav = true){
                TopBar(title = "My Watchlists", alignment = Alignment.Center)


                CreateWatchlistPopup(popupControl, watchlistName, navController);
                newWatchlist(popupControl)
                wathclistList(watchlistList = watchlistList, header ="" , navController = navController )

            };
            BottomBar(navController = navController)
        }

    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CreateWatchlistPopup(openDialog : MutableState<Boolean>, watchlistName : MutableState<String>, navController: NavHostController){



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
                // adding modifier to it.
                Modifier
                    .padding(top= 100.dp).padding(horizontal = 10.dp)
                    // on below line we are adding background color
                    .background(Color(0xFFC4C4C4), RoundedCornerShape(10.dp))

                    .fillMaxWidth(),
                verticalArrangement = Arrangement.Center
                    // on below line we are adding border.

            ) {

                val dataSource = DependencyProvider.getInstance().getWatchlistSource();
                OutlinedTextField(
                    value = watchlistName.value,
                    onValueChange = { watchlistName.value = it },
                    label = { Text("Watchlist Name") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                )

                // Button to create watchlist
                Button(
                    onClick = {
                        // Call your createWatchlist function here
                        dataSource.createWatchlist(watchlistName.value){}
                        openDialog.value = false
                        navController.currentDestination?.let { navController.navigate(it.id) }
                        // You might want to reset the watchlistName after creating a watchlist
                        //watchlistName = ""
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Text("Create Watchlist")
                }

            }
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
            .clickable {  openDialog.value = true;

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
                modifier = Modifier.size(100.dp, 100.dp).background(color=Color.White)
                    .clickable (onClick = { navController.popBackStack()})
                ,

            )
            Text(
                text = watchlist.name,
                fontSize = 20.sp,
                overflow = TextOverflow.Ellipsis,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier.size(200.dp, 98.dp)
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