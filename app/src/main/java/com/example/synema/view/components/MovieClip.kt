package com.example.synema.view.components
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.unit.dp


@Composable
fun MovieClip(bitmap: ImageBitmap) {
        Card(
            shape = RoundedCornerShape(0.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
        ) {
            Image(
                bitmap = bitmap,
                contentDescription = "NULL",
                modifier = Modifier.fillMaxSize()
            )

        }
}