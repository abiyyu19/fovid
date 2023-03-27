package com.dicoding.picodiploma.fovid.view.province

import android.app.SearchManager
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.picodiploma.fovid.R
import com.dicoding.picodiploma.fovid.databinding.ActivityProvinceBinding
import com.dicoding.picodiploma.fovid.model.response.ListDataItem
import okhttp3.internal.filterList

class ProvinceActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProvinceBinding
    private val provinceViewModel by viewModels<ProvinceViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProvinceBinding.inflate(layoutInflater)
        setContentView(binding.root)

        provinceViewModel.getProvince()

        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView = binding.edtSearch

        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))
        searchView.queryHint = resources.getString(R.string.search_hint)

        provinceViewModel.listProvince.observe(this) { listProvince->

            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String): Boolean {
                    return false
                }

                override fun onQueryTextChange(newText: String): Boolean {
                    setProvince(listProvince.sortedBy {
                        it.key
                    }.filterList {
                        this.key.contains(newText.uppercase())
                    })
                    return true
                }
            })

            setProvince(listProvince.sortedBy {
                it.key
            })
        }

        provinceViewModel.isLoading.observe(this) {
            showLoading(it)
        }

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

    private fun setProvince(province: List<ListDataItem>) {
        val layoutManager = LinearLayoutManager(this)
        binding.rvProvince.layoutManager = layoutManager

        val adapter = ProvinceAdapter(province)
        binding.rvProvince.adapter = adapter
    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }
}