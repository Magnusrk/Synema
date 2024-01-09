package com.example.synema.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.navigation.compose.rememberNavController
import com.example.synema.model.ProfileModel

class MainViewModel : ViewModel() {
    var appJustLaunched by mutableStateOf(true)
    var userIsAuthenticated by mutableStateOf(false)

}