package com.dicoding.picodiploma.fovid.view.province

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dicoding.picodiploma.fovid.R
import com.dicoding.picodiploma.fovid.databinding.ActivityProvinceDetailBinding
import com.dicoding.picodiploma.fovid.model.response.ListDataItem

class ProvinceDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProvinceDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProvinceDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupData()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

    private fun setupData() {
        val provinceDetail = intent.getParcelableExtra<ListDataItem>(EXTRA_DATA)
        if (provinceDetail != null) {
            binding.tvStatistic.text = getString(R.string.province_report, provinceDetail.key)
            binding.tvCaseCount.text = String.format("%,d", provinceDetail.jumlahKasus)
            binding.tvRecoverCount.text = String.format("%,d", provinceDetail.jumlahSembuh)
            binding.tvDieCount.text = String.format("%,d", provinceDetail.jumlahMeninggal)
            binding.tvHospitalizedCount.text = String.format("%,d", provinceDetail.jumlahDirawat)
        }
        supportActionBar?.title = provinceDetail?.key
    }

    companion object {
        const val EXTRA_DATA = "extra_data"
    }
}