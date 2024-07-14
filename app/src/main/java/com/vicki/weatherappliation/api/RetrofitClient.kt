package com.vicki.weatherappliation.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitClient {

    val logging = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(logging)
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .build()

        private const val WEATHER_API = "https://api.weatherapi.com";


        private const val BASE_URL = "http://127.0.0.1:8080/api/";


        private fun getWeatherInstance() : Retrofit {
            return Retrofit.Builder()
                .baseUrl(WEATHER_API)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

    private fun getLocalApiInstance() : Retrofit {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }



        val weatherApi : WeatherApi = getWeatherInstance().create(WeatherApi::class.java)
        val localApi : LocalApi = getLocalApiInstance().create(LocalApi::class.java)



}