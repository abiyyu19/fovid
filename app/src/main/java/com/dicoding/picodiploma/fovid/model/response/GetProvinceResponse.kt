package com.dicoding.picodiploma.fovid.model.response

import com.google.gson.annotations.SerializedName

data class GetProvinceResponse(

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("curr_val")
	val currVal: String,

	@field:SerializedName("results")
	val results: List<ResultsItem>
)

data class ResultsItem(

	@field:SerializedName("value")
	val value: String,

	@field:SerializedName("key")
	val key: String
)
