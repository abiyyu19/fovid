package com.dicoding.picodiploma.fovid.model.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class ProvinceResponse(

	@field:SerializedName("missing_data")
	val missingData: Float,

	@field:SerializedName("tanpa_provinsi")
	val tanpaProvinsi: Int,

	@field:SerializedName("current_data")
	val currentData: Float,

	@field:SerializedName("list_data")
	val listData: List<ListDataItem>,

	@field:SerializedName("last_date")
	val lastDate: String
)

@Parcelize
data class JenisKelaminItem(

	@field:SerializedName("doc_count")
	val docCount: Int,

	@field:SerializedName("key")
	val key: String
): Parcelable

@Parcelize
data class ListDataItem(

	@field:SerializedName("penambahan")
	val penambahanProv: PenambahanProv,

	@field:SerializedName("doc_count")
	val docCount: Double,

	@field:SerializedName("lokasi")
	val lokasi: Lokasi,

	@field:SerializedName("jumlah_meninggal")
	val jumlahMeninggal: Int,

	@field:SerializedName("jumlah_kasus")
	val jumlahKasus: Int,

	@field:SerializedName("jumlah_sembuh")
	val jumlahSembuh: Int,

	@field:SerializedName("jenis_kelamin")
	val jenisKelamin: List<JenisKelaminItem>,

	@field:SerializedName("key")
	val key: String,

	@field:SerializedName("jumlah_dirawat")
	val jumlahDirawat: Int
): Parcelable

@Parcelize
data class PenambahanProv(

	@field:SerializedName("meninggal")
	val meninggal: Int,

	@field:SerializedName("positif")
	val positif: Int,

	@field:SerializedName("sembuh")
	val sembuh: Int
): Parcelable

@Parcelize
data class Lokasi(

	@field:SerializedName("lon")
	val lon: Double,

	@field:SerializedName("lat")
	val lat: Double
): Parcelable
