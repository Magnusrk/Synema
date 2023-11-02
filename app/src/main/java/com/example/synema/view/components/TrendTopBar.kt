package com.example.synema.view.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.synema.R


@Composable
fun TrendTopBar(
   // search: Boolean = false,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(272.dp) // Adjust the height as needed
           // .background(Color.Transparent) // You can set a transparent background

    ){
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ){
            val imageModifier = Modifier
                .size(400.dp)
            Image(
                painter = painterResource(id = R.drawable.intersteller),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = imageModifier

            )
        }
    }
}
