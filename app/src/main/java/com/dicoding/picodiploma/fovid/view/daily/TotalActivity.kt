package com.dicoding.picodiploma.fovid.view.daily

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import com.dicoding.picodiploma.fovid.R
import com.dicoding.picodiploma.fovid.databinding.ActivityTotalBinding

class TotalActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTotalBinding
    private val dailyViewModel by viewModels<DailyViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTotalBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dailyViewModel.isLoading.observe(this) {
            showLoading(it)
        }

        dailyViewModel.getDaily()

        dailyViewModel.dataPenambahan.observe(this) {
            binding.tvStatDesc.text = getString(R.string.stat_desc, it.tanggal)
        }

        dailyViewModel.dataTotal.observe(this) { total ->
            binding.tvPositiveCount.text = String.format("%,d", total.jumlahPositif)
            binding.tvRecoverCount.text = String.format("%,d", total.jumlahSembuh)
            binding.tvDieCount.text = String.format("%,d", total.jumlahMeninggal)
            binding.tvHospitalizedCount.text = String.format("%,d", total.jumlahDirawat)
        }

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
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