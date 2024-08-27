package com.vicki.weatherappliation.vm

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.vicki.weatherappliation.api.NetworkResponse
import com.vicki.weatherappliation.api.ResponseData
import com.vicki.weatherappliation.api.RetrofitClient
import com.vicki.weatherappliation.model.WeatherDataModel
import com.vicki.weatherappliation.model.auth.Data
import com.vicki.weatherappliation.model.auth.LoginResponseModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.json.JSONException

class AuthVM : ViewModel() {

    var tag = "AuthVM";

    private val authApi = RetrofitClient.localApi

    private val loginMutableResponse = MutableLiveData<NetworkResponse<LoginResponseModel>>()
    val loginLiveResponse: MutableLiveData<NetworkResponse<LoginResponseModel>> = loginMutableResponse


    private val registerMutableResponse = MutableLiveData<NetworkResponse<LoginResponseModel>>()
    val registerLiveResponse: MutableLiveData<NetworkResponse<LoginResponseModel>> = registerMutableResponse


    fun callRegister(userName: String,phone: String, password: String) {
        val jsonObject = JsonObject();
        try {
            jsonObject.addProperty("userName", userName);
            jsonObject.addProperty("phoneNumber", phone);
            jsonObject.addProperty("password", password);
        } catch (e: JSONException) {
            e.printStackTrace();
        }

        registerMutableResponse.value = NetworkResponse.Loading
        viewModelScope.launch {
            try {
                val response = authApi.callRegisterApi(jsonObject)
                if (response.isSuccessful) {
                    response.body()?.let {
                        registerMutableResponse.value = NetworkResponse.Success(it)
                    }

                } else {
                    Log.e(tag, "Message :${response.body()?.message}")
                    registerMutableResponse.value = NetworkResponse.Error(
                        response.body()?.message.toString()
                    )
                }
            } catch (e: Exception) {
                registerMutableResponse.value = NetworkResponse.Error(e.message.toString())
                Log.e("Check", e.message.toString())
            }

        }
    }


    fun callLogin(phone: String, password: String) {
        val jsonObject = JsonObject();
        try {
            jsonObject.addProperty("phoneNumber", phone);
            jsonObject.addProperty("password", password);
        } catch (e: JSONException) {
            e.printStackTrace();
        }

        loginMutableResponse.value = NetworkResponse.Loading
        viewModelScope.launch {
            try {
                val response = authApi.callLoginApi(jsonObject)
                if (response.isSuccessful) {
                    response.body()?.let {
                        loginMutableResponse.value = NetworkResponse.Success(it)
                    }

                } else {
                    Log.e(tag, "Message :${response.body()?.message}")
                    loginMutableResponse.value = NetworkResponse.Error(
                        response.body()?.message.toString()
                    )
                }
            } catch (e: Exception) {
                loginMutableResponse.value = NetworkResponse.Error(e.message.toString())
                Log.e("Check", e.message.toString())
            }

        }
    }
}

