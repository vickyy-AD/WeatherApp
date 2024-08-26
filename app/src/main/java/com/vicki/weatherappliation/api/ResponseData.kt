package com.vicki.weatherappliation.api

import com.google.gson.annotations.SerializedName


data class ResponseData<T>(
     @SerializedName("status")
     val status: Boolean? = null,

     @SerializedName("message")
     val message: String? = null,

     @SerializedName("data")
     val data: T? = null
)