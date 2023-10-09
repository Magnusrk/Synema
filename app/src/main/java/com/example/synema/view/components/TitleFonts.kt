package com.example.synema.view.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.synema.R

@Composable
fun TitleFont() {
    val titleFont = FontFamily(
        Font(R.font.inter_bold, FontWeight.Bold)
    )


    Text(
        "Intersteller",
        fontFamily = titleFont,
        fontWeight = FontWeight.Bold,

        modifier = Modifier
            .graphicsLayer(alpha = 0.90f)
            .drawWithCache {
                onDrawWithContent {
                    drawContent()
                }
            },
        fontSize = 30.sp
    )
}