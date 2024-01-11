package com.example.synema.view.screens
import GradientBox
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.synema.R
import com.example.synema.view.components.BottomBar
import com.example.synema.view.components.LoadingWrapper
import com.example.synema.view.components.MainContainer
import com.example.synema.view.components.TopBar
import com.example.synema.viewmodel.Watchlists.MyListViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyListScreen(
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
                title = "My List",
                alignment = Alignment.Center,
                backArrow = true,
                navController = vm.getNav()
            )
            MainContainer(hasBottomNav = true) {

                    ListDetails(vm = vm, watchlistID = watchlistID)

            }
            BottomBar(navController = vm.getNav())

            }
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListDetails(vm : MyListViewModel, watchlistID: String?){
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
                onClick = { /*On back*/ }) {
                Image(
                    painter = painterResource(id = R.drawable.heart),
                    contentDescription = null,
                    modifier = Modifier.size(30.dp),
                    colorFilter = ColorFilter.tint(Color.White)
                )
            }
            Surface(
                modifier = Modifier.size(30.dp),
                color = Color(0, 0, 0, 0),
                onClick = {
                    if (watchlistID != null) {
                        vm.deleteMovieFromWatchlist(watchlistID, movie1.id)
                    }
                }) {

                Image(
                    painter = painterResource(id = R.drawable.edit_playlist),
                    contentDescription = null,
                    modifier = Modifier.size(30.dp).background(Color.White),
                    colorFilter = ColorFilter.tint(Color.White)
                )
            }


        }
    }
}

