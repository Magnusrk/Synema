package com.example.synema

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.synema.ui.theme.SynemaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SynemaTheme {
                // A surface container using the 'background' color from the theme
                LoginPage()
            }
        }
    }
    @Composable
    fun LoginPage() {

        val brush = Brush.verticalGradient(
            listOf(
                Color(0xFF430B3D),
                Color(0xFF16202F)
            ))
                // A surface container using the 'background' color from the theme
                Box(modifier = Modifier
                    .fillMaxSize()
                    .background(brush=brush)


                    , ) {
                    Logo();
                }
    }

    @Composable
    fun Logo() {
        val textGrad = Brush.verticalGradient(
            listOf(
                Color(0xFF5531E5),
                Color(0xFFD49721)
            )
        )
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.width(300.dp)
                .padding(30.dp)

        ) {
            Text(
                "SYNEMA",
                modifier = Modifier.graphicsLayer(alpha = 0.99f)
                    .drawWithCache {
                        onDrawWithContent {
                            drawContent()
                            drawRect(textGrad, blendMode = BlendMode.SrcAtop)
                        }
                    },
                fontSize = 48.sp
            )

        }
    }

}
