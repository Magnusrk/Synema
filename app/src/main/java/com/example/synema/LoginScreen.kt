package com.example.synema

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.consumedWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.view.KeyEventDispatcher.Component
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest


import com.example.synema.ui.theme.SynemaTheme



    @Composable
     fun LoginScreen(navController : NavHostController) {
        GradientBox(){
            ContentContainer();
        }
    }

    @Composable
    fun GradientBox(boxContent : @Composable () -> Unit){
        val brush = Brush.verticalGradient(
            listOf(
                Color(0xFF430B3D),
                Color(0xFF16202F)
            ))
        // A surface container using the 'background' color from the theme
        Box(modifier = Modifier
            .fillMaxSize()
            .background(brush = brush)
            , ){
              boxContent.invoke()
        }
    }


    @Composable
    private fun ContentContainer(){
        Column (
            modifier = Modifier
                .fillMaxSize()
                .padding(19.dp)
            ){
            SynHeader()
            MovieDisplay();

        }

    }

    @Composable
    private fun MovieDisplay(){
        MoviePosterFrame()
        /*
        AsyncImage(
            model = "https://i.scdn.co/image/ab6761610000e5ebba025c8f62612b2ca6bfa375",
            contentDescription = null,
            modifier = Modifier.size(100.dp)
        )
         */
    }

    @Composable
    private fun MoviePosterFrame(){
        Box(
            modifier = Modifier.size(width = 106.dp, height = 161.dp)
                .background(color= Color.LightGray)
                .border(5.dp, SolidColor(Color.LightGray), shape = RoundedCornerShape(5.dp))

        )

    }

    @Composable
    private fun SynHeader() {
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
        Column(

            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 77.dp)

        ) {
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
    }
