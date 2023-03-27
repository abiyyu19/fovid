package com.dicoding.picodiploma.fovid.view.faskes

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dicoding.picodiploma.fovid.R
import com.dicoding.picodiploma.fovid.databinding.ActivityFaskesDetailBinding
import com.dicoding.picodiploma.fovid.model.response.DataItem

class FaskesDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFaskesDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFaskesDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = getString(R.string.faskes_detail)

        setupData()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

    private fun setupData() {
        val faskesDetail = intent.getParcelableExtra<DataItem>(EXTRA_DATA)
        if (faskesDetail?.jenisFaskes == "") {
            binding.tvFaskesType.text = getString(R.string.not_available)
        } else {
            binding.tvFaskesType.text = faskesDetail?.jenisFaskes
        }
        binding.tvFaskesName.text = faskesDetail?.nama ?: getString(R.string.not_available)
        binding.tvFaskesStatus.text = faskesDetail?.status ?: getString(R.string.not_available)
        binding.tvTelp.text = faskesDetail?.telp ?: getString(R.string.not_available)
        binding.tvAddress.text = faskesDetail?.alamat ?: getString(R.string.not_available)

        binding.btnMaps.setOnClickListener {
            val gmmIntentUri = Uri.parse("geo:${faskesDetail?.latitude},${faskesDetail?.longitude}?z=17")
            val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
            mapIntent.setPackage("com.google.android.apps.maps")
            startActivity(mapIntent)
        }
    }

    companion object {
        const val EXTRA_DATA = "extra_data"
    }
}