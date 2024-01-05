package com.example.synema.view.components

import androidx.compose.foundation.clickable
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.synema.R

@Composable
fun SynemaLogo(){
    val syncFam = FontFamily(
        Font(R.font.syncopate_regular, FontWeight.Normal),
        Font(R.font.syncopate_bold, FontWeight.Bold)
    )
    val textGrad = Brush.verticalGradient(
        listOf(
            Color(0xFF5531E5),
            Color(0xFFD49721)
        )
    )

    Text(
        "SYNEMA",
        fontFamily = syncFam,
        fontWeight = FontWeight.Bold,

        modifier = Modifier
            .graphicsLayer(alpha = 0.99f)
            .drawWithCache {
                onDrawWithContent {
                    drawContent()
                    drawRect(textGrad, blendMode = BlendMode.SrcAtop)
                }
            },
        fontSize = 40.sp
    )
}