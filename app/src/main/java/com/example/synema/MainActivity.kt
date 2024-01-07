package com.example.synema

import android.content.Context
import android.content.Intent
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
import com.example.synema.Data.DependencyProvider
import com.example.synema.model.ProfileModel
import com.example.synema.view.screens.AddMovieToWatchlist
import com.example.synema.view.screens.HomeScreen
import com.example.synema.view.screens.LoginScreen
import com.example.synema.view.screens.MediaDetails
import com.example.synema.view.screens.MyListScreen
import com.example.synema.view.screens.SearchScreen
import com.example.synema.view.screens.Profile
import com.example.synema.view.screens.SignupScreen
import com.example.synema.view.screens.WatchList


class MainActivity : ComponentActivity() {
    companion object {
        private val key = "text"
        fun create(context: Context, text: String? = null): Intent =
            Intent(context, MainActivity::class.java).putExtra(
                key, text
            )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val DEBUG = false
        DependencyProvider.getInstance().create(DEBUG);

        setContent {
            val navController = rememberNavController()
            val profileState = remember {
                mutableStateOf(ProfileModel(
                    id = "-1",
                    name = "",
                    email = "",
                    bio = ""
                ))
            }

            // A surface container using the 'background' color from the theme
            NavHost(navController = navController, startDestination = "watchlists") {
                    composable("login") { LoginScreen(navController, profileState) }
                    composable("signup") { SignupScreen(navController, profileState) }
                    composable("home") { HomeScreen(navController, profileState) }
                    composable("search") {SearchScreen(navController, profileState)}
                    composable("watchlists") { WatchList(navController, profileState) }
                    composable("watchlists/{watchlist_id}",
                        arguments = listOf(navArgument("watchlist_id") { type = NavType.StringType }))
                        { backStackEntry ->
                            MyListScreen(navController, profileState, backStackEntry.arguments?.getString("watchlist_id")) }
                    composable("profile") { Profile(navController, profileState) }
                    composable("mediaDetails/{movieID}",
                        arguments = listOf(navArgument("movieID") { type = NavType.StringType }))
                        { backStackEntry ->
                            MediaDetails(navController, profileState, backStackEntry.arguments?.getString("movieID")) }
                    composable("mediaDetails/{movieID}/save",
                        arguments = listOf(navArgument("movieID") { type = NavType.StringType }))
                    { backStackEntry ->
                        AddMovieToWatchlist(navController, profileState, backStackEntry.arguments?.getString("movieID"))
                    }


            }
        }
    }

}
