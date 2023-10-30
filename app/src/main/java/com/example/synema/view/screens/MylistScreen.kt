package com.example.synema.view.screens

import GradientBox
import MoviePosterFrame
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.synema.model.ProfileModel
import com.example.synema.view.components.BottomBar
import com.example.synema.view.components.MainContainer
import com.example.synema.view.components.SynemaLogo
import com.example.synema.view.components.TopBar
import com.example.synema.view.utils.Size

@Composable
fun List(navController : NavHostController, profileState: MutableState<ProfileModel>) {
    GradientBox(){
        Column {
            MainContainer(hasBottomNav = true){
                TopBar(title = "My List", alignment = Alignment.Center)
            };
            BottomBar(navController = navController)
        }

    }
}

