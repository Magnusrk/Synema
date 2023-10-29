package com.example.synema.view.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.synema.view.utils.Size

@Composable
fun MainContainer(hasBottomNav : Boolean = false, content : @Composable () -> Unit = {} ){
    val size = Size();
    var containerHeight =size.height();
    if (hasBottomNav) containerHeight -= 80;
    Column(
        modifier = Modifier
            .height(containerHeight.dp)
            .verticalScroll(rememberScrollState())

    ) {
        //TopBar("My Watchlist", Alignment.Center, 30.sp, true, false)
        content.invoke()
    }
}