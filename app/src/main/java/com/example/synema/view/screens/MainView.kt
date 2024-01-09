package com.example.synema.view.screens

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.synema.controller.AppContext


@Composable
fun MainView() {
    val navController = rememberNavController()
    val profileState = AppContext.getInstance().getProfileState()
    AppContext.setNav(navController)

    val startDestination = "login"
    // A surface container using the 'background' color from the theme
    NavHost(navController = navController, startDestination = startDestination) {
        composable("login") { LoginScreen() }
        composable("signup") { SignupScreen() }
        composable("home") { HomeScreen() }
        composable("search") { SearchScreen(navController, profileState) }
        composable("watchlists") { WatchList(navController) }
        composable("watchlists/{watchlist_id}",
            arguments = listOf(navArgument("watchlist_id") { type = NavType.StringType })
        )
        { backStackEntry ->
            MyListScreen(
                navController,
                profileState,
                backStackEntry.arguments?.getString("watchlist_id")
            )
        }
        composable("profile") { Profile(navController, profileState) }
        composable("mediaDetails/{movieID}",
            arguments = listOf(navArgument("movieID") { type = NavType.StringType })
        )
        { backStackEntry ->
            MediaDetails(
                navController,
                profileState,
                backStackEntry.arguments?.getString("movieID")
            )
        }
        composable("mediaDetails/{movieID}/save",
            arguments = listOf(navArgument("movieID") { type = NavType.StringType })
        )
        { backStackEntry ->
            AddMovieToWatchlist(
                navController,
                profileState,
                backStackEntry.arguments?.getString("movieID")
            )
        }
        composable("mediaDetails/{movieID}/review",
            arguments = listOf(navArgument("movieID") { type = NavType.StringType })
        )
        { backStackEntry ->
            WriteReviewScreen(
                navController,
                profileState,
                backStackEntry.arguments?.getString("movieID")
            )
        }
    }
}