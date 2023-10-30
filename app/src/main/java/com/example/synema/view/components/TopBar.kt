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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.synema.R
import com.example.synema.view.screens.onChange

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
    navController: NavController? = null

){
    var alpha = 1f;
    if(transparent) alpha = 0.0f;
    Box(
        modifier = Modifier
        .fillMaxWidth()
        .height(103.dp)
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
                    fontSize = fontSize,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    modifier = Modifier
                        .align(alignment)
                        .padding(20.dp)
                )
            }

        var text by remember { mutableStateOf("") }

        if(textInput) {
            TextField(
                value = text,
                onValueChange = { text = it; onChange(text) },
                modifier = Modifier.padding(start = 60.dp, end = 60.dp).align(alignment),
                singleLine = true,
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color(0, 0, 0, 0),
                    textColor = Color.White,
                    unfocusedLabelColor = Color.White,
                    focusedLabelColor = Color.White,
                    unfocusedIndicatorColor = Color(0xFFC5AC29),
                    focusedIndicatorColor = Color(0xFF811C77),
                )
            )
        }

        if (search) {
            Image(
                painter = painterResource(id = R.drawable.magniglas),
                contentDescription = null,
                modifier = Modifier
                    .padding(16.dp)
                    .align(Alignment.CenterEnd)
                    .size(30.dp)
            )
        }

    }
}