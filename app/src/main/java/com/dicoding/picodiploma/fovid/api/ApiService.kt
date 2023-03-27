package com.dicoding.picodiploma.fovid.api

import com.dicoding.picodiploma.fovid.model.response.*
import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @GET("update.json")
    fun getDaily(): Call<DailyResponse>

    @GET("prov.json")
    fun getProvince(): Call<ProvinceResponse>

    @POST("get-province")
    fun getProvinceList(): Call<GetProvinceResponse>

    @FormUrlEncoded
    @POST("get-city")
    fun getCityList(
        @Field("start_id") province: String
    ): Call<GetCityResponse>

    @GET("get-faskes-vaksinasi")
    fun getFaskes(
        @Query("skip") skip: Int,
        @Query("city") city: String
    ): Call<FaskesResponse>

    @GET("rs.json")
    fun getHospital(): Call<List<HospitalResponse>>
}