package com.example.synema.view.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.synema.model.ProfileModel
import com.example.synema.viewmodel.LoginViewModel
import com.example.synema.viewmodel.MainViewModel

@Composable
fun MainView(mainViewModel: MainViewModel) {
    val navController = rememberNavController()
    val profileState = remember {
        mutableStateOf(
            mainViewModel.userContext
        )
    }

    if(mainViewModel.appJustLaunched){
        mainViewModel.appJustLaunched = false;
    }

    var startDestination = "login"
    if(mainViewModel.userIsAuthenticated){
        startDestination = "home";
    }


    // A surface container using the 'background' color from the theme
    NavHost(navController = navController, startDestination = startDestination) {
        composable("login") { LoginScreen(LoginViewModel(navController, profileState)) }
        composable("signup") { SignupScreen(navController, profileState) }
        composable("home") { HomeScreen(navController, profileState) }
        composable("search") { SearchScreen(navController, profileState) }
        composable("watchlists") { WatchList(navController, profileState) }
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
    }
}