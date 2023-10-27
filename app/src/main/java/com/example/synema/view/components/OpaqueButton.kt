package com.example.synema.view.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextDecoration

@Composable
fun OpaqueButton(label : String, onClick: () -> Unit, modifier: Modifier = Modifier){
    Button(onClick = {onClick()},
        contentPadding = PaddingValues(),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0,0,0,0)
        ),

        modifier = modifier

    ){
        Text(label, color=Color.White,
            style = TextStyle(textDecoration = TextDecoration.Underline)
        )
    }
}
