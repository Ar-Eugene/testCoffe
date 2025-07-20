package com.example.testcoffe.core

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.testcoffe.core.theme.TestCoffeTheme
import com.example.testcoffe.menu.presentation.ui.MenuScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TestCoffeTheme {
                MenuScreen(locationId = 1L)
            }
        }
    }
}