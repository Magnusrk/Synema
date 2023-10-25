package com.example.synema

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.synema.model.ProfileModel
import com.example.synema.view.screens.LoginScreen
import com.example.synema.view.screens.HomeScreen
import com.example.synema.view.screens.MediaDetails
import com.example.synema.view.screens.SignupScreen


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val navController = rememberNavController()
            val profileState = remember {
                mutableStateOf(ProfileModel(
                    id = "-1",
                    name = "",
                    email = ""
                ))
            }

            // A surface container using the 'background' color from the theme
            NavHost(navController = navController, startDestination = "login") {
                    composable("login") { LoginScreen(navController, profileState) }
                    composable("signup") { SignupScreen(navController, profileState) }
                    composable("home") { HomeScreen(navController, profileState) }
                    composable("mediaDetails/{movieID}",
                        arguments = listOf(navArgument("movieID") { type = NavType.StringType }))
                        { backStackEntry ->
                            MediaDetails(navController, profileState, backStackEntry.arguments?.getString("movieID")) }

            }
        }
    }

}
