package com.example.synema.view.screens

import GradientBox
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.navigation.NavHostController
import com.example.synema.controller.AppContext
import com.example.synema.model.ProfileModel
import com.example.synema.view.components.BottomBar
import com.example.synema.view.components.MainContainer
import com.example.synema.view.components.TopBar

fun MyReviews(navController : NavHostController, profileState: MutableState<ProfileModel>) {
    val context = AppContext.getInstance();
    GradientBox(){
        Column {
            MainContainer(hasBottomNav = true){
                TopBar(title = "My Profile", Alignment.Center)

            }
            BottomBar(navController = navController)
        }

    }
}

/*@Composable
fun Profile(navController : NavHostController, profileState: MutableState<ProfileModel>) {
    val context = AppContext.getInstance();
    GradientBox(){
        Column {
            MainContainer(hasBottomNav = true){
                TopBar(title = "My Profile", Alignment.Center)
                EditProfileButton()
                ProfileNameHeader(name = context.getProfileState().value.name)
                ProfilePicture()
                FollowersReviewsStatus(76, 88)
                PersonalDescription()
                Directories(context.getNav())

            }
            BottomBar(navController = navController)
        }

    }
}*/