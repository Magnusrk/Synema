package com.example.synema.view.screens

import GradientBox
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.synema.controller.AppContext
import com.example.synema.model.ProfileModel
import com.example.synema.view.components.BottomBar
import com.example.synema.view.components.MainContainer
import com.example.synema.view.components.TopBar

@Composable
fun MyReviews(navController: NavHostController, profileState: MutableState<ProfileModel>) {
    GradientBox() {
        MainContainer(hasBottomNav = true) {
            TopBar(title = "My Reviews", Alignment.Center)
            Reviews()

        }
    }

}

@Composable
private fun Reviews() {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        ReviewCard(text = " ")
    }
}


@Composable
private fun ReviewCard(text: String) {
    Box(
        modifier = Modifier
            .padding(16.dp)
            .width(350.dp)
            .height(150.dp)
            .background(Color(0xFFB15FA8), shape = RoundedCornerShape(10.dp))
    )

    Text(
        text = text,
        color = Color(0xFFD9D9D9),
        fontSize = 17.sp,
        fontWeight = FontWeight.Bold,
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
    )

}