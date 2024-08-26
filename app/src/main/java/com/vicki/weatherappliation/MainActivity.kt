package com.vicki.weatherappliation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.vicki.weatherappliation.api.AppConstants
import com.vicki.weatherappliation.composables.HomeScreen
import com.vicki.weatherappliation.composables.LoginScreen
import com.vicki.weatherappliation.composables.RegisterScreen
import com.vicki.weatherappliation.composables.SplashScreen
import com.vicki.weatherappliation.vm.AuthVM
import com.vicki.weatherappliation.vm.WeatherVM

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.statusBarColor = resources.getColor(R.color.black)


        //   val weatherViewModel = ViewModelProvider(this)[WeatherVM::class.java]
        // val loginViewModel = ViewModelProvider(this)[AuthVM::class.java]
        setContent {
            //  ScreenRoutes(viewModel = weatherViewModel,loginViewModel)
            ScreenRoutes()
        }


    }
}


@Composable
fun ScreenRoutes() {


    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = AppConstants.RouteConstants.SPLASH
    )
    {
        composable(route = AppConstants.RouteConstants.SPLASH) {
            SplashScreen(navController)
        }

        composable(route = AppConstants.RouteConstants.LOGIN) {
            LoginScreen(navController, LocalContext.current)
        }

        composable(route = AppConstants.RouteConstants.REGISTER) {
            RegisterScreen(navController, LocalContext.current)
        }

        composable(route = AppConstants.RouteConstants.HOME) {
            HomeScreen()
        }


    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SimpleToolbar(headerText: String, onNavigationClick: () -> Unit) {
    TopAppBar(
        colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = Color.Blue
        ),

        title = {
            Text(
                text = headerText,
                color = Color.White,
                fontSize = 20.sp,  // Adjust the text size here
            )
        },
        navigationIcon = {
            IconButton(onClick = onNavigationClick) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back",
                    tint = Color.White
                )
            }
        },

        )
}



