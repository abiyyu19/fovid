package com.dicoding.picodiploma.fovid.view.hospital

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dicoding.picodiploma.fovid.api.ApiConfig
import com.dicoding.picodiploma.fovid.model.response.HospitalResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HospitalViewModel : ViewModel() {

    private val _listHospital = MutableLiveData<List<HospitalResponse>?>()
    val listHospital: LiveData<List<HospitalResponse>?> = _listHospital

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun getHospital() {
        _isLoading.value = true
        val client = ApiConfig.getApiService().getHospital()
        client.enqueue(object : Callback<List<HospitalResponse>> {
            override fun onResponse(
                call: Call<List<HospitalResponse>>,
                response: Response<List<HospitalResponse>>
            ) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    _listHospital.value = response.body()
                    Log.d("masuk sini", response.body()?.size.toString())

                } else {
                    Log.e(ContentValues.TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<List<HospitalResponse>>, t: Throwable) {
                _isLoading.value = false
                Log.d(ContentValues.TAG, "onFailure: ${t.message}")
                Log.d("lah iya", "masuk sini")
            }
        })
    }
}