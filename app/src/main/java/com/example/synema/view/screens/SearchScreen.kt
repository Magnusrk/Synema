package com.example.synema.view.screens

import GradientBox
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.synema.model.ProfileModel
import com.example.synema.ui.theme.SynemaTheme
import com.example.synema.view.components.TopBar

@Composable
 fun SearchScreen(navController : NavHostController, profileState : MutableState<ProfileModel>) {
    SynemaTheme {
        GradientBox() {
            MovieList(navController, profileState)
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieList(navController: NavHostController, profileState: MutableState<ProfileModel>) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        TopBar("", Alignment.CenterStart, 20.sp, backArrow = true, transparent = true, search = false, textInput = true, navController = navController)
        /*
        LazyVerticalGrid(
            columns = GridCells.Adaptive(minSize = 128.dp)
        ){

        }

         */
    }
}

fun onChange(text: String) {

}

