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
    private val _loginResult = MutableLiveData<NetworkResponse<LoginResponseModel>>()
     val loginResult : MutableLiveData<NetworkResponse<LoginResponseModel>> = _loginResult

//    private val _loginResult = MutableStateFlow<ResponseData<LoginResponseModel>?>(null)
//    val loginResult: StateFlow<ResponseData<LoginResponseModel>?> = _loginResult



    fun callLogin(phone: String, password: String) {
         val jsonObject = JsonObject();
        try {
            jsonObject.addProperty("phoneNumber", phone);
            jsonObject.addProperty("password", password);
        } catch (e: JSONException) {
            e.printStackTrace();
        }

        _loginResult.value = NetworkResponse.Loading
         viewModelScope.launch {
            try {
                val response = authApi.callLoginApi(jsonObject)
                if (response.isSuccessful) {
                    response.body()?.let {
                        _loginResult.value = NetworkResponse.Success(it)
                     }

                } else {
                    Log.e(tag,"Message :${response.body()?.message}")
                  _loginResult.value = NetworkResponse.Error(
                      response.body()?.message.toString()
                  )
                }
            } catch (e: Exception) {
                Log.e("Check", e.message.toString())
            }

        }
    }
}

