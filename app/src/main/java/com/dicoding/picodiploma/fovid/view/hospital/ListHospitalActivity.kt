package com.dicoding.picodiploma.fovid.view.hospital

import android.app.SearchManager
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.picodiploma.fovid.R
import com.dicoding.picodiploma.fovid.databinding.ActivityListHospitalBinding
import com.dicoding.picodiploma.fovid.model.response.HospitalResponse
import okhttp3.internal.filterList

class ListHospitalActivity : AppCompatActivity() {

    private lateinit var binding: ActivityListHospitalBinding
    private val hospitalViewModel by viewModels<HospitalViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListHospitalBinding.inflate(layoutInflater)
        setContentView(binding.root)

        hospitalViewModel.getHospital()

        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView = binding.edtSearch

        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))
        searchView.queryHint = resources.getString(R.string.search_hint_hospital)

        var cityName = intent.getStringExtra(EXTRA_DATA)
        if (cityName != null) {

            if (cityName.contains("KAB. ADM. KEP.")) {
                cityName = cityName.substring(14)

                supportActionBar?.title = "KAB. ADM. KEP. $cityName"
            } else if (cityName.contains("KOTA ADM.")) {
                cityName = cityName.substring(10)

                supportActionBar?.title = "KOTA ADM. $cityName"
            } else if (cityName.contains("KAB.")) {
                cityName = cityName.substring(5)

                supportActionBar?.title = "KAB. $cityName"
            } else {
                if (cityName.contains("TANJUNG PINANG")) {
                    cityName = cityName.substring(5).filter { !it.isWhitespace() }

                    supportActionBar?.title = "KOTA TANJUNG PINANG"
                } else {
                    supportActionBar?.title = cityName
                }
            }

            hospitalViewModel.listHospital.observe(this) { listHospital ->
                if (listHospital != null) {
                    setData(listHospital.filterList {
                        this.wilayah?.contains(cityName) == true
                    })

                    searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                        override fun onQueryTextSubmit(query: String): Boolean {
                            return false
                        }

                        override fun onQueryTextChange(newText: String): Boolean {
                            setData(listHospital.filterList { this.wilayah?.contains(cityName) == true
                            }.filterList {
                                this.nama?.contains(newText.uppercase()) ?:false
                            })
                            return true
                        }
                    })
                }
            }
        }

        hospitalViewModel.isLoading.observe(this) {
            showLoading(it)
        }

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun setData(item : List<HospitalResponse>?) {
        val layoutManager = LinearLayoutManager(this)
        binding.rvHospital.layoutManager = layoutManager

        if (item != null) {
            val adapter = ListHospitalAdapter(item)
            binding.rvHospital.adapter = adapter
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }

    companion object {
        const val EXTRA_DATA = "extra_data"
    }
}