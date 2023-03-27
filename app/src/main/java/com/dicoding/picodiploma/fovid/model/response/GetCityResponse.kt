package com.dicoding.picodiploma.fovid.model.response

import com.google.gson.annotations.SerializedName

data class GetCityResponse(

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("curr_val")
	val currVal: String,

	@field:SerializedName("results")
	val results: List<ResultsItem2>
)

data class ResultsItem2(

	@field:SerializedName("value")
	val value: String,

	@field:SerializedName("key")
	val key: String
)
