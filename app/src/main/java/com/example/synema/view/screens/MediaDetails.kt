package com.example.synema.view.screens
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.synema.view.components.MovieClip
import com.example.synema.view.components.TitleFont
import androidx.compose.foundation.layout.Spacer
import com.example.synema.R
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext


@Composable
fun MediaDetails() {
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        ) {
        Spacer(modifier = Modifier.height(103.dp))
        TitleFont()
        val bitmap: Bitmap = BitmapFactory.decodeResource(context.resources, R.drawable.intersteller)
        val imageBitmap: ImageBitmap = bitmap.asImageBitmap()
        MovieClip(bitmap = imageBitmap)
    }
}




