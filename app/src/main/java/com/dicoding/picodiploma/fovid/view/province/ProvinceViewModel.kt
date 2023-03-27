package com.dicoding.picodiploma.fovid.view.province

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

class ProvinceViewModel : ViewModel() {

    private val _listProvince = MutableLiveData<List<ListDataItem>>()
    val listProvince: LiveData<List<ListDataItem>> = _listProvince

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun getProvince() {
        _isLoading.value = true
        val client = ApiConfig.getApiService().getProvince()
        client.enqueue(object : Callback<ProvinceResponse> {
            override fun onResponse(
                call: Call<ProvinceResponse>,
                response: Response<ProvinceResponse>
            ) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    _listProvince.value = response.body()?.listData
                } else {
                    Log.e(ContentValues.TAG, "onFailure: ${response.message()}")
                    Log.d("Test", "Masuk")
                }
            }

            override fun onFailure(call: Call<ProvinceResponse>, t: Throwable) {
                _isLoading.value = false
                Log.d("test", "ko masuk sini")
                Log.d(ContentValues.TAG, "onFailure: ${t.message}")
            }
        })
    }
}