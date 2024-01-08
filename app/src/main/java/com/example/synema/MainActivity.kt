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
import com.example.synema.view.screens.MainView
import com.example.synema.view.screens.MediaDetails
import com.example.synema.view.screens.MyListScreen
import com.example.synema.view.screens.SearchScreen
import com.example.synema.view.screens.Profile
import com.example.synema.view.screens.SignupScreen
import com.example.synema.view.screens.WatchList
import com.example.synema.viewmodel.MainViewModel


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

        val mainViewModel : MainViewModel = MainViewModel();

        setContent {
            MainView(mainViewModel)
            }
        }
    }

