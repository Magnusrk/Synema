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
import com.example.synema.view.components.BottomBar
import com.example.synema.view.components.MainContainer
import com.example.synema.view.components.SynemaLogo
import com.example.synema.view.components.TopBar
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.TextField
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.sp
import com.example.synema.Data.DependencyProvider
import com.example.synema.R
import com.example.synema.model.WatchlistModel
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupProperties
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.synema.view.components.LoadingWrapper
import com.example.synema.view.components.OpaqueButton
import com.example.synema.viewmodel.Watchlists.WatchlistViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OtherUsersWatchList(userID : String?) {

    val vm: WatchlistViewModel = viewModel()
    if (userID != null) {
        vm.loadOtherUserList(userID)
    }

    GradientBox() {
        LoadingWrapper(vm.isLoading) {
            Column {
                TopBar(title = "Watchlists", alignment = Alignment.Center)
                MainContainer(hasBottomNav = true) {
                    wathclistList(vm)
                };
                BottomBar(navController = vm.getNav())
            }
        }


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
private fun wathclistList(vm: WatchlistViewModel) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 34.dp)

    ) {
        Text(
            text = "",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            modifier = Modifier
                .padding(8.dp)
        )
        Column {
            vm.watchlistList.forEach() { watchlist ->
                watchlistCard(watchlist = watchlist, vm = vm)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun watchlistCard(watchlist: WatchlistModel, vm: WatchlistViewModel) {
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
                .background(color = Color(0xFFB15FA8))
                .clickable(onClick = {
                    vm
                        .getNav()
                        .navigate("watchlists/user/" + watchlist.watchlist_id)
                })
                .padding(0.dp)

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
                .size(150.dp, 98.dp)
                .padding(10.dp)
        )

        Image(
            painter = painterResource(id = R.drawable.delete),
            contentDescription = null,
            modifier = Modifier
                .size(30.dp),
            colorFilter = ColorFilter.tint(Color.Transparent),

            )

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