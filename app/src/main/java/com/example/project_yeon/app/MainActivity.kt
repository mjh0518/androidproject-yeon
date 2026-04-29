package com.example.project_yeon.app

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.fragment.app.FragmentActivity
import androidx.navigation.compose.rememberNavController
import com.example.project_yeon.app.navigation.AppNavHost
import com.example.project_yeon.core.ui.theme.Project_YeonTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : FragmentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
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
