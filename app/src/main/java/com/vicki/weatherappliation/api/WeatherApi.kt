package com.vicki.weatherappliation.api

import com.google.gson.JsonObject
import com.vicki.weatherappliation.model.WeatherDataModel
import com.vicki.weatherappliation.model.auth.LoginResponseModel
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface WeatherApi {

    @GET("/v1/current.json")
    suspend fun getWeather(
        @Query("key") apikey: String,
        @Query("q") city: String
    ): Response<WeatherDataModel>

}

interface LocalApi {

    @POST("auth/login")
    suspend fun callLoginApi(
        @Body requestBody: JsonObject
    ): Response<LoginResponseModel>

    @POST("auth/register")
    suspend fun callRegisterApi(
        @Body requestBody: JsonObject
    ): Response<LoginResponseModel>

}