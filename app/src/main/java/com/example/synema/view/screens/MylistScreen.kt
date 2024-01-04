package com.example.synema.view.screens
import GradientBox
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.synema.R
import com.example.synema.model.ProfileModel
import com.example.synema.view.components.BottomBar
import com.example.synema.view.components.MainContainer
import com.example.synema.view.components.TopBar

/*
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyListScreen(navController: NavHostController, profileState: MutableState<ProfileModel>) {
    val movieList = Datasource().loadMovies()

    GradientBox {
        Column {
            MainContainer(hasBottomNav = true) {
                TopBar(title = "My List", alignment = Alignment.Center)

                movieList.forEach { movie ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(color = Color(0xFF191825)),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = painterResource(movie.imageResourceId),
                            contentDescription = stringResource(movie.stringResourceId),
                            modifier = Modifier.size(95.dp, 98.dp),
                            contentScale = ContentScale.Crop
                        )
                        Text(
                            text = LocalContext.current.getString(movie.stringResourceId),
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White,
                            modifier = Modifier.size(200.dp, 98.dp)
                        )
                        Surface(
                            modifier = Modifier.size(30.dp),
                            color = Color(0, 0, 0, 0),
                            onClick = { navController?.popBackStack() }) {
                            Image(
                                painter = painterResource(id = R.drawable.heart),
                                contentDescription = null,
                                modifier = Modifier.size(30.dp)
                            )
                        }
                        Surface(
                            modifier = Modifier.size(30.dp),
                            color = Color(0, 0, 0, 0),
                            onClick = { navController?.popBackStack() }) {
                            Image(
                                painter = painterResource(id = R.drawable.edit_playlist),
                                contentDescription = null,
                                modifier = Modifier.size(30.dp)
                            )
                        }
                    }
                }
                BottomBar(navController = navController)
            }
        }
    }
}

*/
