package com.example.synema.view.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

@Composable
fun BlackGround(boxContent : @Composable () -> Unit){
    val brush = Brush.verticalGradient(
        listOf(
            Color(0xFF0F0E0F),
            Color(0xFF1A0D1A)
        ))
    // A surface container using the 'background' color from the theme
    Box(modifier = Modifier
        .background(brush = brush)
        .fillMaxSize()
        , ){
        boxContent.invoke()
    }
}
