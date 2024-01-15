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
fun WatchList() {

    val vm: WatchlistViewModel = viewModel()
    vm.loadWatchlists()

    GradientBox() {
        LoadingWrapper(vm.isLoading) {
            Column {
                TopBar(title = "My Watchlists", alignment = Alignment.Center)
                MainContainer(hasBottomNav = true) {
                    CreateWatchlistPopup(vm);
                    CreateDeletePopup(vm)
                    newWatchlist(vm)
                    wathclistList(vm)
                };
                BottomBar(navController = vm.getNav())
            }
        }


    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CreateWatchlistPopup(vm: WatchlistViewModel) {

    if (vm.popupControl.value) {
        Popup(
            // on below line we are adding
            // alignment and properties.
            onDismissRequest = { vm.popupControl.value = false },
            properties = PopupProperties(focusable = true),
            alignment = Alignment.Center,

            ) {

            // on the below line we are creating a box.
            Column(

                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(horizontal = 15.dp)
                // on below line we are adding border.

            ) {
                PopUpHeader(openDialog = vm.popupControl, navController = vm.getNav())
                WatchlistCreationPane(
                    openDialog = vm.popupControl,
                    watchlistName = vm.newWatchlistName,
                    navController = vm.getNav()
                )
                Row(
                    modifier = Modifier
                        .height(50.dp)
                        .fillMaxWidth()
                        .background(
                            Color(0xFF430B3D),
                            shape = RoundedCornerShape(bottomStart = 8.dp, bottomEnd = 8.dp)
                        )
                        .padding(bottom = 10.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically

                ) {
                    // Button to create watchlist
                    Button(
                        onClick = {
                            vm.createWatchlist()

                        },
                        shape = RoundedCornerShape(20),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF811C77)),
                        contentPadding = PaddingValues(horizontal = 20.dp)
                    ) {
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
private fun CreateDeletePopup(vm: WatchlistViewModel) {

    if (vm.deleteConfirmationPopupControl.value) {
        Popup(
            onDismissRequest = { vm.deleteConfirmationPopupControl.value = false },
            properties = PopupProperties(focusable = true),
            alignment = Alignment.TopCenter,
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(10.dp)

            ) {
                // Row with buttons
                DeletePopupButton(vm)

                // Text for the delete confirmation

            }
        }
    }
}

@Composable
private fun DeletePopupButton(vm: WatchlistViewModel) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(175.dp)
            .background(color = Color(0xFF63105B), shape = RoundedCornerShape(5.dp)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly

    ) {
        Text(
            text = "Are you sure you want to delete this watchlist?",
            color = Color.White,
            fontSize = 16.sp,
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            textAlign = TextAlign.Center
        )
        Row(horizontalArrangement = Arrangement.SpaceEvenly, modifier = Modifier.fillMaxWidth()) {
            // Cancel Button
            Button(
                onClick = { vm.deleteConfirmationPopupControl.value = false },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF811C77)),
            ) {
                Text(text = "Cancel")
            }
            // Delete Button
            Button(
                onClick = { vm.DeleteWatchlist() },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFAD2F2F)),
            ) {
                Text(text = "Delete")
            }
        }

    }
}


@Composable
private fun PopUpHeader(openDialog: MutableState<Boolean>, navController: NavHostController) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .background(
                color = Color(0xFF63105B),
                shape = RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp)
            )
            .padding(5.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row() {
            Column(modifier = Modifier.align(Alignment.CenterVertically)) {
                OpaqueButton(
                    label = "Cancel",
                    onClick = { openDialog.value = false },
                    modifier = Modifier.align(Alignment.Start)
                )
            }
            Column(
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .fillMaxWidth()
            ) {
                Text(
                    "Create watchlist",
                    color = Color.White,
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    fontSize = 20.sp
                )
            }


        }


    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun WatchlistCreationPane(
    openDialog: MutableState<Boolean>,
    watchlistName: MutableState<String>,
    navController: NavHostController
) {

    Row(
        modifier = Modifier
            .height(100.dp)
            .background(Color(0xFF430B3D))
    ) {
        Row() {
            TextField(
                value = watchlistName.value,
                onValueChange = { watchlistName.value = it },
                label = { Text("Watchlist Name") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
                    .padding(horizontal = 20.dp)
            )
        }


    }

}


@Composable
private fun MovieDisplay() {
    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .height(250.dp)
    ) {
        MoviePosterFrame(
            Arrangement.Bottom,
            "https://static.posters.cz/image/750/plakater/interstellar-ice-walk-i23290.jpg"
        )
        MoviePosterFrame(
            Arrangement.Center,
            "https://i.etsystatic.com/10683147/r/il/d4a024/4900691314/il_1080xN.4900691314_fu21.jpg"
        )
        MoviePosterFrame(
            Arrangement.Top,
            "https://www.hollywoodreporter.com/wp-content/uploads/2023/06/French-Film-Poster-Barbie-Warner-Bros..jpg?w=999"
        )
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
private fun newWatchlist(vm: WatchlistViewModel) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .offset(34.dp, 33.dp)
            .verticalScroll(rememberScrollState())
    ) {

        Box(modifier = Modifier
            .height(100.dp)
            .width(100.dp)
            .background(Color(0xFFB15FA8))
            .clickable {
                vm.popupControl.value = true;

            }
        )

        {
            Image(
                painter = painterResource(id = R.drawable.actual_plus_symbol),
                contentDescription = null,
                modifier = Modifier
                    .align(Alignment.Center)
                    .width(100.dp)
                    .height(100.dp)
            )
        }
        Text(
            text = "New Watchlist", fontSize = 16.sp, color = Color.White, modifier = Modifier
                .align(Alignment.CenterVertically)
                .padding(27.dp)
        )
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
                        .navigate("watchlists/" + watchlist.watchlist_id)
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
                .size(30.dp)
                .clickable(onClick = {
                    vm.promptDeletion(watchlist.watchlist_id)
                }),
            colorFilter = ColorFilter.tint(Color.White),


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