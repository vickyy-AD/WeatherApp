package com.vicki.weatherappliation.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.vicki.weatherappliation.api.AppConstants
import kotlinx.coroutines.delay
import com.vicki.weatherappliation.R


@Composable
fun SplashScreen(navController: NavHostController) {
    LaunchedEffect(Unit) {
        delay(1000)
        navController.navigate(AppConstants.RouteConstants.LOGIN){
            popUpTo(AppConstants.RouteConstants.SPLASH) { inclusive = true }
        }
    }
    Surface {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()

        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_launcher_foreground),
                    contentDescription = null, Modifier.size(100.dp),
                    colorFilter = ColorFilter.tint(Color.Blue)
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = "Splash Screen", fontSize = 24.sp, fontWeight = FontWeight.Bold,
                 )
            }

        }


    }
}