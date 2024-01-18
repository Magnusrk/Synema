package com.example.synema.view.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun LoadingWrapper(isLoading: MutableState<Boolean>, hasLogo : Boolean = false, content: @Composable () -> Unit = {}){
    if(isLoading.value){
        Column (modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            if(hasLogo){
                SynemaLogo()
            }

            CircularProgressIndicator()
        }

    } else{
        content.invoke()
    }
}