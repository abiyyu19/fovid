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
import com.dicoding.picodiploma.fovid.databinding.ActivityHospitalBinding
import com.dicoding.picodiploma.fovid.model.response.ResultsItem
import com.dicoding.picodiploma.fovid.view.faskes.FaskesViewModel
import okhttp3.internal.filterList

class HospitalActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHospitalBinding
    private val faskesViewModel by viewModels<FaskesViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHospitalBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        faskesViewModel.getProvinceName()

        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView = binding.edtSearch

        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))
        searchView.queryHint = resources.getString(R.string.search_hint)

        faskesViewModel.listProvinceName.observe(this) { listProvinceName ->
            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String): Boolean {
                    return false
                }

                override fun onQueryTextChange(newText: String): Boolean {
                    setData(listProvinceName.filterList {
                        this.value.contains(newText.uppercase())
                    })
                    return true
                }
            })

            setData(listProvinceName)
        }

        faskesViewModel.isLoading.observe(this) {
            showLoading(it)
        }
    }

    private fun setData(item : List<ResultsItem>) {
        val layoutManager = LinearLayoutManager(this)
        binding.rvProvince.layoutManager = layoutManager

        val adapter = ProvinceNameAdapter(item)
        binding.rvProvince.adapter = adapter
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
}