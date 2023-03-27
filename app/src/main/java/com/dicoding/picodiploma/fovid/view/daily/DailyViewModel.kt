package com.dicoding.picodiploma.fovid.view.daily

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dicoding.picodiploma.fovid.api.ApiConfig
import com.dicoding.picodiploma.fovid.model.response.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DailyViewModel : ViewModel() {
    private val _dataTotal = MutableLiveData<Total>()
    val dataTotal: LiveData<Total> = _dataTotal

    private val _dataPenambahan = MutableLiveData<Penambahan>()
    val dataPenambahan: LiveData<Penambahan> = _dataPenambahan

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun getDaily() {
        _isLoading.value = true
        val client = ApiConfig.getApiService().getDaily()
        client.enqueue(object : Callback<DailyResponse> {
            override fun onResponse(
                call: Call<DailyResponse>,
                response: Response<DailyResponse>
            ) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    _dataTotal.value = response.body()?.update?.total
                    _dataPenambahan.value = response.body()?.update?.penambahan
                } else {
                    Log.e(ContentValues.TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<DailyResponse>, t: Throwable) {
                _isLoading.value = false
                Log.d(ContentValues.TAG, "onFailure: ${t.message}")
            }
        })
    }
}