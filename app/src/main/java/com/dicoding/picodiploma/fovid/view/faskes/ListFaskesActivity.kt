package com.dicoding.picodiploma.fovid.view.faskes

import android.app.SearchManager
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.picodiploma.fovid.R
import com.dicoding.picodiploma.fovid.databinding.ActivityListFaskesBinding
import com.dicoding.picodiploma.fovid.model.response.DataItem
import okhttp3.internal.filterList

class ListFaskesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityListFaskesBinding
    private val listFaskesViewModel by viewModels<ListFaskesViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListFaskesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val cityName = intent.getStringExtra(EXTRA_DATA)
        if (cityName != null) {
            listFaskesViewModel.getFaskes(cityName)

            supportActionBar?.title = cityName
        }

        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView = binding.edtSearch

        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))
        searchView.queryHint = resources.getString(R.string.search_hint_faskes)

        listFaskesViewModel.listFaskes.observe(this) { listFaskes ->
            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String): Boolean {
                    return false
                }

                override fun onQueryTextChange(newText: String): Boolean {
                    setData(listFaskes?.filterList {
                        this.nama?.contains(newText.uppercase()) ?:false
                    })
                    return true
                }
            })

            setData(listFaskes)
        }

        listFaskesViewModel.isLoading.observe(this) {
            showLoading(it)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

    private fun setData(item : List<DataItem>?) {
        val layoutManager = LinearLayoutManager(this)
        binding.rvFaskes.layoutManager = layoutManager

        val adapter = ListFaskesAdapter(item)
        binding.rvFaskes.adapter = adapter
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