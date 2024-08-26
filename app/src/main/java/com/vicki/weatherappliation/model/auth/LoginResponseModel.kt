package com.vicki.weatherappliation.model.auth

import com.google.gson.annotations.SerializedName

data class LoginResponseModel(

    @SerializedName("status")
    val status: Boolean,

    @SerializedName("message")
    val message: String,

    @SerializedName("data")
    val data: Data
)