package com.dicoding.picodiploma.fovid.view.faskes

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dicoding.picodiploma.fovid.api.ApiConfig2
import com.dicoding.picodiploma.fovid.model.response.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FaskesViewModel : ViewModel(){

    private val _listProvinceName = MutableLiveData<List<ResultsItem>>()
    val listProvinceName: LiveData<List<ResultsItem>> = _listProvinceName

    private val _listCityName = MutableLiveData<List<ResultsItem2>>()
    val listCityName: LiveData<List<ResultsItem2>> = _listCityName

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun getProvinceName() {
        _isLoading.value = true
        val client = ApiConfig2.getApiService().getProvinceList()
        client.enqueue(object : Callback<GetProvinceResponse> {
            override fun onResponse(
                call: Call<GetProvinceResponse>,
                response: Response<GetProvinceResponse>
            ) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    _listProvinceName.value = response.body()?.results
                } else {
                    Log.e(ContentValues.TAG, "onFailure: ${response.message()}")
                    Log.d("Test", "Masuk")
                }
            }

            override fun onFailure(call: Call<GetProvinceResponse>, t: Throwable) {
                _isLoading.value = false
                Log.d(ContentValues.TAG, "onFailure: ${t.message}")
            }
        })
    }

    fun getCityName(province: String) {
        _isLoading.value = true
        val client = ApiConfig2.getApiService().getCityList(province)
        client.enqueue(object : Callback<GetCityResponse> {
            override fun onResponse(
                call: Call<GetCityResponse>,
                response: Response<GetCityResponse>
            ) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    _listCityName.value = response.body()?.results
                } else {
                    Log.e(ContentValues.TAG, "onFailure: ${response.message()}")
                    Log.d("Test", "Masuk")
                }
            }

            override fun onFailure(call: Call<GetCityResponse>, t: Throwable) {
                _isLoading.value = false
                Log.d(ContentValues.TAG, "onFailure: ${t.message}")
            }
        })
    }
}