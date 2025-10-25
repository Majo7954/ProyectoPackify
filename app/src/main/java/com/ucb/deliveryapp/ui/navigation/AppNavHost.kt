package com.ucb.deliveryapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.ucb.deliveryapp.ui.screens.home.HomeScreen
import com.ucb.deliveryapp.ui.screens.login.LoginScreen
import com.ucb.deliveryapp.ui.screens.register.RegisterScreen

object Routes {
    const val LOGIN = "login"
    const val REGISTER = "register"
    const val HOME = "home"
}

@Composable
fun AppNavHost(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Routes.LOGIN) {
        composable(Routes.LOGIN) {
            LoginScreen(
                onNavigateToRegister = { navController.navigate(Routes.REGISTER) },
                onLoginSuccess = { navController.navigate(Routes.HOME) }
            )
        }
        composable(Routes.REGISTER) {
            RegisterScreen(onRegisterSuccess = { navController.popBackStack() })
        }
        composable(Routes.HOME) {
            HomeScreen()
        }
    }
}
