package com.vicki.weatherappliation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.vicki.weatherappliation.api.AppConstants
import com.vicki.weatherappliation.composables.HomeScreen
import com.vicki.weatherappliation.composables.LoginScreen
import com.vicki.weatherappliation.composables.SplashScreen
import com.vicki.weatherappliation.vm.AuthVM
import com.vicki.weatherappliation.vm.WeatherVM

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val weatherViewModel = ViewModelProvider(this)[WeatherVM::class.java]
        val loginViewModel = ViewModelProvider(this)[AuthVM::class.java]
        setContent {
            ScreenRoutes(viewModel = weatherViewModel,loginViewModel)
         }
    }
}



@Composable
fun ScreenRoutes(viewModel: WeatherVM, loginViewModel: AuthVM) {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = AppConstants.RouteConstants.SPLASH)
    {
        composable(route = AppConstants.RouteConstants.SPLASH) {
            SplashScreen(navController)
        }

        composable(route = AppConstants.RouteConstants.LOGIN) {
            LoginScreen(navController,loginViewModel)
        }

        composable(route = AppConstants.RouteConstants.HOME) {
            HomeScreen(viewModel)
        }


    }
}



