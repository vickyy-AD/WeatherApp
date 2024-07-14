package com.vicki.weatherappliation.model.auth

data class LoginResponseModel(
    val data: Data,
    val message: String,
    val status: Boolean
)