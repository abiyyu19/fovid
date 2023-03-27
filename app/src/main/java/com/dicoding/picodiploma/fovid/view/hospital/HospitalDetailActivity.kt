package com.dicoding.picodiploma.fovid.view.hospital

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dicoding.picodiploma.fovid.R
import com.dicoding.picodiploma.fovid.databinding.ActivityHospitalBinding
import com.dicoding.picodiploma.fovid.databinding.ActivityHospitalDetailBinding
import com.dicoding.picodiploma.fovid.model.response.DataItem
import com.dicoding.picodiploma.fovid.model.response.HospitalResponse
import com.dicoding.picodiploma.fovid.view.faskes.FaskesDetailActivity

class HospitalDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHospitalDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHospitalDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = getString(R.string.hospital_detail)

        setupData()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

    private fun setupData() {
        val hospitalDetail = intent.getParcelableExtra<HospitalResponse>(EXTRA_DATA)
        if (hospitalDetail?.telepon == "") {
            binding.tvTelp.text = getString(R.string.not_available)
        } else {
            binding.tvTelp.text = hospitalDetail?.telepon
        }

        if (hospitalDetail?.alamat == "") {
            binding.tvAddress.text = getString(R.string.not_available)
        } else {
            binding.tvAddress.text = hospitalDetail?.alamat
        }

        binding.tvHospitalName.text = hospitalDetail?.nama ?: getString(R.string.not_available)
        binding.tvHospitalRegion.text = hospitalDetail?.wilayah ?: getString(R.string.not_available)
        binding.btnMaps.setOnClickListener {
            val gmmIntentUri = Uri.parse("geo:${hospitalDetail?.lokasi?.lat},${hospitalDetail?.lokasi?.lon}?z=17")
            val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
            mapIntent.setPackage("com.google.android.apps.maps")
            startActivity(mapIntent)
        }
    }

    companion object {
        const val EXTRA_DATA = "extra_data"
    }
}