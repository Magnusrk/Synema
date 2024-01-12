package com.example.synema.view.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.synema.R

@Composable
fun TitleFont(title : String) {
    val titleFont = FontFamily(
        Font(R.font.inter_bold, FontWeight.Bold)
    )

    Text(
        title,
        fontFamily = titleFont,
        fontWeight = FontWeight.Bold,
        color = Color.White,
        modifier = Modifier
            .graphicsLayer(alpha = 0.90f)
            .padding(10.dp)
            .drawWithCache {
                onDrawWithContent {
                    drawContent()
                }
            },
        fontSize = 30.sp
    )
}