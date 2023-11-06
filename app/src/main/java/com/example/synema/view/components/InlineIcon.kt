package com.example.synema.view.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun InlineIcon(@DrawableRes resourceID : Int, tint : Color = Color.White, size: Dp = 25.dp, spacing : Dp = 5.dp){
    Image(
        modifier = Modifier
            .padding(start = spacing)
            .size(size),
        painter = painterResource(resourceID),
        contentDescription = null,
        contentScale = ContentScale.FillHeight,
        colorFilter = ColorFilter.tint(tint),
    )
}