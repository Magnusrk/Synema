package com.example.synema.view.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.synema.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    title: String? = null,
    alignment: Alignment = Alignment.CenterStart,
    fontSize: TextUnit = 20.sp,
    backArrow: Boolean = false,
    search: Boolean = false,
    transparent : Boolean = false,
    textInput : Boolean = false,
    navController: NavController? = null,
    onChange :  (String) -> Unit = { },
    inputLabel : String = "",
    filter  : Boolean = false,
    filterPopUp :  () -> Unit = {}

){
    val syncopateFamily = FontFamily(
        Font(R.font.syncopate_regular, FontWeight.Normal),
        Font(R.font.syncopate_bold, FontWeight.Bold)
    )
    var alpha = 1f;
    if(transparent) alpha = 0.0f;
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .background(color = Color(0xFF430B3D).copy(alpha)),

    ){
        if (backArrow) {
            Surface(modifier = Modifier.fillMaxHeight(),
                color = Color(0,0,0,0),
                onClick = {navController?.popBackStack() },
                ) {
                Image(
                    painter = painterResource(id = R.drawable.arrow),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(16.dp)
                        .align(Alignment.CenterStart)
                        .size(24.dp)
                )
            }

        }

            if (!title.isNullOrBlank()) {
                Text(
                    title,
                    fontFamily = syncopateFamily,
                    fontSize = fontSize,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    modifier = Modifier
                        .align(alignment)
                        .padding(20.dp),
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1
                )
            }

        var text by remember { mutableStateOf("") }

        if(textInput) {
            TextField(
                value = text,
                onValueChange = { text = it; onChange(text) },
                modifier = Modifier.padding(top = 15.dp, start = 60.dp, end = 60.dp),
                singleLine = true,
                label = { Text(inputLabel) }
                ,
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color(0, 0, 0, 0),
                    unfocusedLabelColor = Color.White,
                    focusedLabelColor = Color.White,
                    unfocusedIndicatorColor = Color(0xFFC5AC29),
                    focusedIndicatorColor = Color(0xFF811C77),
                    unfocusedTextColor = Color(0xFFBF76FF),
                    focusedTextColor = Color.White
                )
            )
            if(filter){
                OpaqueButton(label = "filter", onClick = { filterPopUp.invoke() }, modifier= Modifier.align(
                    Alignment.CenterEnd))
            }
        }

        if (search) {
            Surface(modifier = Modifier
                .fillMaxHeight()
                .align(Alignment.CenterEnd),
                color = Color(0,0,0,0),
                onClick = {navController?.navigate("search")},
            ) {
                Image(
                    painter = painterResource(id = R.drawable.magniglas),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(16.dp)
                        .size(35.dp)
                )
            }
        }

    }
}