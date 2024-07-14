package com.vicki.weatherappliation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import com.vicki.weatherappliation.composables.HomeScreen
import com.vicki.weatherappliation.ui.theme.WeatherAppliationTheme
import com.vicki.weatherappliation.vm.WeatherVM

class MainActivity : ComponentActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val weatherViewModel = ViewModelProvider(this)[WeatherVM::class.java]
        setContent {
            WeatherAppliationTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    HomeScreen(viewModel = weatherViewModel)
                }
            }
        }
    }
}

