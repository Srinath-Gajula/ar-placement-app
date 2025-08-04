package com.example.drillar

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.*
import androidx.navigation.navArgument
import com.example.drillar.ui.screens.ARPlacementScreen
import com.example.drillar.ui.screens.DrillSelectionScreen
import com.example.drillar.ui.theme.ARLearnerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ARLearnerTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = "drill_selection",
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        composable("drill_selection") {
                            DrillSelectionScreen(navController)
                        }
                        composable(
                            "ar_screen/{drillName}",
                            arguments = listOf(navArgument("drillName") { type = NavType.StringType })
                        ) { backStackEntry ->
                            val drillName = backStackEntry.arguments?.getString("drillName") ?: "Drill 1"
                            ARPlacementScreen(drillName)
                        }
                    }
                }
            }
        }
    }
}