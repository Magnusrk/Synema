package com.example.synema.view.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.synema.model.ProfileModel

@Composable
fun AvatarList(navController: NavHostController, profileState: MutableState<ProfileModel>) {
    val avatarUrls = listOf(
        "https://postimg.cc/tndFgyMg",
    )

    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        contentPadding = PaddingValues(8.dp)
    ) {
        items(avatarUrls.size) { index ->
            AvatarItem(
                avatarUrl = avatarUrls[index],
                navController = navController,
                onAvatarSelected = { selectedAvatarUrl ->
                    profileState.value.profilePicture = selectedAvatarUrl
                    navController.navigate("editprofile")
                }
            )
        }
    }
}

@Composable
fun AvatarItem(avatarUrl: String, navController: NavHostController, onAvatarSelected: (String) -> Unit) {
    Box(
        modifier = Modifier
            .width(80.dp)
            .height(80.dp)
            .padding(4.dp)
            .clickable {
                onAvatarSelected(avatarUrl)
            }
    ) {
        AsyncImage(
            model = avatarUrl,
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
    }

}

