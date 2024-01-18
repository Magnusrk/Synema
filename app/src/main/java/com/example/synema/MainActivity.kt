package com.example.synema

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.synema.Data.DependencyProvider
import com.example.synema.view.screens.MainView


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
            MainView()
            }
        }
    }

