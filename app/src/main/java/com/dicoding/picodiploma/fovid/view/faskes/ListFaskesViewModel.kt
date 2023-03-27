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

class ListFaskesViewModel : ViewModel() {

    private val _listFaskes = MutableLiveData<List<DataItem>?>()
    val listFaskes: LiveData<List<DataItem>?> = _listFaskes

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun getFaskes(city: String) {
        _isLoading.value = true
        val client = ApiConfig2.getApiService().getFaskes(0, city)
        client.enqueue(object : Callback<FaskesResponse> {
            override fun onResponse(
                call: Call<FaskesResponse>,
                response: Response<FaskesResponse>
            ) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    _listFaskes.value = response.body()?.data as List<DataItem>?
                } else {
                    Log.e(ContentValues.TAG, "onFailure: ${response.message()}")
                    Log.d("Test", "Masuk")
                }
            }

            override fun onFailure(call: Call<FaskesResponse>, t: Throwable) {
                _isLoading.value = false
                Log.d(ContentValues.TAG, "onFailure: ${t.message}")
            }
        })
    }
}