package com.dicoding.picodiploma.fovid.model.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class HospitalResponse(

	@field:SerializedName("tempat_tidur")
	val tempatTidur: Int? = null,

	@field:SerializedName("nama")
	val nama: String? = null,

	@field:SerializedName("lokasi")
	val lokasi: Lokasirs? = null,

	@field:SerializedName("telepon")
	val telepon: String? = null,

	@field:SerializedName("wilayah")
	val wilayah: String? = null,

	@field:SerializedName("tipe")
	val tipe: String? = null,

	@field:SerializedName("kode_rs")
	val kodeRs: String? = null,

	@field:SerializedName("alamat")
	val alamat: String? = null
): Parcelable

@Parcelize
data class Lokasirs(

	@field:SerializedName("lon")
	val lon: Double? = null,

	@field:SerializedName("lat")
	val lat: Double? = null
): Parcelable
