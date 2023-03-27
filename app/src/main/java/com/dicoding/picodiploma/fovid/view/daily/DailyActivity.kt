package com.dicoding.picodiploma.fovid.view.daily

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import com.dicoding.picodiploma.fovid.R
import com.dicoding.picodiploma.fovid.databinding.ActivityDailyBinding

class DailyActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDailyBinding
    private val dailyViewModel by viewModels<DailyViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDailyBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dailyViewModel.isLoading.observe(this) {
            showLoading(it)
        }

        dailyViewModel.getDaily()

        dailyViewModel.dataPenambahan.observe(this) { penambahan ->
            binding.tvStatDesc.text = getString(R.string.stat_desc, penambahan.tanggal)
            binding.tvPositiveCount.text = String.format("%,d", penambahan.jumlahPositif)
            binding.tvRecoverCount.text = String.format("%,d", penambahan.jumlahSembuh)
            binding.tvDieCount.text = String.format("%,d", penambahan.jumlahMeninggal)
            binding.tvHospitalizedCount.text = String.format("%,d", penambahan.jumlahDirawat)
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