package com.vicki.weatherappliation.model.auth

import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("phoneNumber")
    val phoneNumber: String,

    @SerializedName("userId")
    val userId: Int,

    @SerializedName("userName")
    val userName: String
)