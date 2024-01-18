package com.example.synema.view.components

import androidx.compose.foundation.layout.height
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.synema.R



@Composable
fun BottomBar(navController: NavHostController){
    val tabs = listOf("home", "watchlists", "feed", "socials", "profile")
    val icons = listOf(R.drawable.theater_icon,R.drawable.eye_icon, R.drawable.feed_icon, R.drawable.social_icon, R.drawable.profile_icon)
    val current = navController.currentBackStackEntry?.destination?.route


    NavigationBar (containerColor = Color(0xFF430B3D), modifier = Modifier.height(55.dp)

        ){
        tabs.forEachIndexed { index, item ->
            NavigationBarItem(
                icon = { InlineIcon(resourceID = icons[index], tint= Color.White, spacing= 0.dp) },
                selected = item == current,
                onClick = { if(current != item) navController.navigate(item) },
                colors = NavigationBarItemDefaults.colors(indicatorColor = Color(0xFF270724)),

            )
        }
    }

}
