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
import com.dicoding.picodiploma.fovid.databinding.ActivityListCityHospitalBinding
import com.dicoding.picodiploma.fovid.model.response.ResultsItem2
import com.dicoding.picodiploma.fovid.view.faskes.FaskesViewModel
import okhttp3.internal.filterList

class ListCityHospitalActivity : AppCompatActivity() {

    private lateinit var binding: ActivityListCityHospitalBinding
    private val faskesViewModel by viewModels<FaskesViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListCityHospitalBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val provinceName = intent.getStringExtra(EXTRA_DATA)
        if (provinceName != null) {
            faskesViewModel.getCityName(provinceName)

            supportActionBar?.title = provinceName
        }

        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView = binding.edtSearch

        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))
        searchView.queryHint = resources.getString(R.string.search_hint_city)

        faskesViewModel.listCityName.observe(this) { listCityName->

            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String): Boolean {
                    return false
                }

                override fun onQueryTextChange(newText: String): Boolean {
                    setData(listCityName.filterList {
                        this.value.contains(newText.uppercase())
                    })
                    return true
                }
            })

            setData(listCityName)
        }

        faskesViewModel.isLoading.observe(this) {
            showLoading(it)
        }
    }

    private fun setData(item : List<ResultsItem2>) {
        val layoutManager = LinearLayoutManager(this)
        binding.rvCity.layoutManager = layoutManager

        val adapter = CityNameAdapter(item)
        binding.rvCity.adapter = adapter
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