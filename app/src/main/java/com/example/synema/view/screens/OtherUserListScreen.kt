package com.example.synema.view.screens
import GradientBox
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupProperties
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.synema.Data.DependencyProvider
import com.example.synema.R
import com.example.synema.view.components.BottomBar
import com.example.synema.view.components.LoadingWrapper
import com.example.synema.view.components.MainContainer
import com.example.synema.view.components.TopBar
import com.example.synema.viewmodel.Watchlists.MyListViewModel
import com.example.synema.viewmodel.Watchlists.WatchlistViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OtherUserListScreen(
    watchlistID: String?
) {

    val vm : MyListViewModel = viewModel()


    if (watchlistID != null) {
        vm.getWatchlist(watchlistID)
    }



    GradientBox {
        LoadingWrapper (vm.isLoading){
        Column {
            TopBar(
                title = "List",
                alignment = Alignment.Center,
                backArrow = true,
                navController = vm.getNav()
            )
            MainContainer(hasBottomNav = true) {

                    CreateDeletePopup(vm = vm)

                    ListDetails(vm = vm, watchlistID = watchlistID)

            }
            BottomBar(navController = vm.getNav())

            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ListDetails(vm : MyListViewModel, watchlistID: String?){
    vm.movielist.forEach() { movie1 ->
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Color(0xFF191825)),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = movie1.poster_url,
                contentDescription = null,
                modifier = Modifier
                    .width(95.dp)
                    .height(135.dp)
                    .clickable { vm.clickMovie(movie1.id) },
                contentScale = ContentScale.FillBounds
            )
            Text(
                text = movie1.title,
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier.size(200.dp, 98.dp)
            )
            Surface(
                modifier = Modifier.size(30.dp),
                color = Color(0, 0, 0, 0),
                onClick = {
                    if (watchlistID != null) {
                        vm.promptDeletion(movie1.id.toString(), watchlistID)
                    }
                }) {
                /*
                Image(
                    painter = painterResource(id = R.drawable.delete),
                    contentDescription = null,
                    modifier = Modifier.size(30.dp),
                    colorFilter = ColorFilter.tint(Color.White)
                )

                 */
            }


        }
    }
}


@Composable
private fun CreateDeletePopup( vm : MyListViewModel) {

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
private fun DeletePopupButton(vm : MyListViewModel) {



    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(175.dp)
            .background(color = Color(0xFF63105B), shape = RoundedCornerShape(5.dp)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly

    ) {
        Text(
            text = "Are you sure you want to delete this movie?",
            color = Color.White,
            fontSize = 16.sp,
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            textAlign = TextAlign.Center
        )
        Row(horizontalArrangement = Arrangement.SpaceEvenly, modifier = Modifier.fillMaxWidth()){
            // Cancel Button
            Button(onClick = { vm.deleteConfirmationPopupControl.value = false },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF811C77)),
            ){
                Text(text = "Cancel")
            }
            // Delete Button
            Button(
                onClick = { vm.deleteMovieFromWatchlist()},
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFAD2F2F)),
            ){
                Text(text="Delete")
            }
        }

    }
}

