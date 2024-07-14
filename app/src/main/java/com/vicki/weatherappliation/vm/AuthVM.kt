package com.vicki.weatherappliation.vm

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.JsonObject
import com.vicki.weatherappliation.api.NetworkResponse
import com.vicki.weatherappliation.api.RetrofitClient
import com.vicki.weatherappliation.model.auth.LoginResponseModel
import kotlinx.coroutines.launch
import org.json.JSONException

class AuthVM : ViewModel() {

    private val authApi = RetrofitClient.localApi
    private val _loginResult = MutableLiveData<NetworkResponse<LoginResponseModel>>()
    val loginResult: LiveData<NetworkResponse<LoginResponseModel>> = _loginResult


    fun callLogin(phone: String, password: String) {
        Log.e("Check", "1")
        var jsonObject = JsonObject();
        try {
            jsonObject.addProperty("phoneNumber", phone);
            jsonObject.addProperty("password", password);
        } catch (e: JSONException) {
            e.printStackTrace();
        }
        Log.e("Check", "2")

        _loginResult.value = NetworkResponse.Loading
        viewModelScope.launch {
            try {
                val response = authApi.callLoginApi(jsonObject)
                if (response.isSuccessful) {
                    response.body()?.let {
                        _loginResult.value = NetworkResponse.Success(it)
                    }
                    Log.e("Check", "3")

                } else {
                    Log.e("Check", "4")

                    _loginResult.value = NetworkResponse.Error("Failed to load data")
                }
            } catch (e: Exception) {
                Log.e("Check", "5")
                Log.e("Check", e.message.toString())
                Log.e("Check", e.toString())

                _loginResult.value = NetworkResponse.Error("Failed to load data")
            }

        }
    }

}