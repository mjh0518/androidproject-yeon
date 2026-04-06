package com.example.project_yeon.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.example.project_yeon.app.navigation.AppNavHost
import com.example.project_yeon.core.ui.theme.Project_YeonTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            Project_YeonTheme {
                AppNavHost(navController = navController)
            }
        }
    }
}
