package com.vicki.weatherappliation.api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    val client = OkHttpClient.Builder()
         .build()

        private const val WEATHER_API = "https://api.weatherapi.com";


        private const val BASE_URL = "http://127.0.0.1:8080/api";


        private fun getWeatherInstance() : Retrofit {
            return Retrofit.Builder()
                .baseUrl(WEATHER_API)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

    private fun getLocalApiInstance() : Retrofit {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }



        val weatherApi : WeatherApi = getWeatherInstance().create(WeatherApi::class.java)
        val localApi : LocalApi = getWeatherInstance().create(LocalApi::class.java)



}