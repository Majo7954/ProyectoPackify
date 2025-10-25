package com.ucb.deliveryapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.ucb.deliveryapp.ui.navigation.AppNavHost
import com.ucb.deliveryapp.ui.theme.DeliveryappTheme

@OptIn(ExperimentalMaterial3Api::class)
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            DeliveryappTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    DeliveryApp()
                }
            }
        }
    }
}

@Composable
fun DeliveryApp() {
    val navController = rememberNavController()
    AppNavHost(navController = navController)
}
