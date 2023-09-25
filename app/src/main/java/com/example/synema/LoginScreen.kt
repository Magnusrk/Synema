package com.example.synema

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.consumedWindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardElevation
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.draw.shadow
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
import androidx.compose.ui.unit.Dp
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
                .padding(14.dp)
            ){
            SynHeader()
            MovieDisplay();

        }

    }

    @Composable
    private fun MovieDisplay(){
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)

        ){
            MoviePosterFrame(Arrangement.Bottom, "https://static.posters.cz/image/750/plakater/interstellar-ice-walk-i23290.jpg")
            MoviePosterFrame(Arrangement.Center, "https://i.etsystatic.com/10683147/r/il/d4a024/4900691314/il_1080xN.4900691314_fu21.jpg")
            MoviePosterFrame(Arrangement.Top, "https://www.hollywoodreporter.com/wp-content/uploads/2023/06/French-Film-Poster-Barbie-Warner-Bros..jpg?w=999")
        }

        /*

         */
    }

    @Composable
    private fun MoviePosterFrame(arrangement : Arrangement.Vertical, img : String){
        Column(
            verticalArrangement = arrangement,
            modifier = Modifier.fillMaxHeight(),

        ) {

            Card(
                shape = RoundedCornerShape(5.dp),
                modifier = Modifier
                    .size(width = 106.dp, height = 161.dp)







            ){
                AsyncImage(
                    model = img,
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize()
                )
            }

        }


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
