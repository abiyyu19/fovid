package com.dicoding.picodiploma.fovid.view.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.*
import com.dicoding.picodiploma.fovid.R
import com.dicoding.picodiploma.fovid.databinding.ActivityMainBinding
import com.dicoding.picodiploma.fovid.view.about.AboutActivity
import com.dicoding.picodiploma.fovid.view.daily.DailyActivity
import com.dicoding.picodiploma.fovid.view.daily.TotalActivity
import com.dicoding.picodiploma.fovid.view.faskes.FaskesActivity
import com.dicoding.picodiploma.fovid.view.hospital.HospitalActivity
import com.dicoding.picodiploma.fovid.view.province.ProvinceActivity
import kotlin.system.exitProcess

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.elevation = 0F;

        binding.cvDailyButton.setOnClickListener {
            val intent = Intent(this, DailyActivity::class.java)
            startActivity(intent)
        }

        binding.cvTotalButton.setOnClickListener {
            val intent = Intent(this, TotalActivity::class.java)
            startActivity(intent)
        }

        binding.cvProvinceButton.setOnClickListener {
            val intent = Intent(this, ProvinceActivity::class.java)
            startActivity(intent)
        }

        binding.cvFaskesButton.setOnClickListener {
            val intent = Intent(this, FaskesActivity::class.java)
            startActivity(intent)
        }

        binding.cvHospitalButton.setOnClickListener {
            val intent = Intent(this, HospitalActivity::class.java)
            startActivity(intent)
        }

        binding.cvExit.setOnClickListener {
            finish()
            exitProcess(0)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.option_menu, menu)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.aboutButton -> {
                val intent = Intent(this, AboutActivity::class.java)
                startActivity(intent)
                true
            }
            else -> true
        }
    }
}