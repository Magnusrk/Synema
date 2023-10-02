package com.example.synema

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.synema.view.screens.LoginScreen

class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



        setContent {
            val navController = rememberNavController()
                // A surface container using the 'background' color from the theme
            NavHost(navController = navController, startDestination = "login") {
                    composable("login") { LoginScreen(navController) }


            }
        }
    }








}
