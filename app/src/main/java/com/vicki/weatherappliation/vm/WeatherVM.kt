package com.vicki.weatherappliation.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vicki.weatherappliation.api.AppConstants
import com.vicki.weatherappliation.api.NetworkResponse
import com.vicki.weatherappliation.api.RetrofitClient
import com.vicki.weatherappliation.model.WeatherDataModel
import kotlinx.coroutines.launch

class WeatherVM : ViewModel() {

    private val weatherApi = RetrofitClient.weatherApi
    private val _weatherResult = MutableLiveData<NetworkResponse<WeatherDataModel>>()
    val weatherResult : LiveData<NetworkResponse<WeatherDataModel>> = _weatherResult



    fun getData(city : String){
        _weatherResult.value = NetworkResponse.Loading
        viewModelScope.launch {
            try{
                val response = weatherApi.getWeather(AppConstants.API_KEY,city)
                if(response.isSuccessful){
                    response.body()?.let {
                        _weatherResult.value = NetworkResponse.Success(it)
                    }
                }else{
                    _weatherResult.value = NetworkResponse.Error("Failed to load data")
                }
            }
            catch (e : Exception){
                _weatherResult.value = NetworkResponse.Error("Failed to load data")
            }

        }
    }

}