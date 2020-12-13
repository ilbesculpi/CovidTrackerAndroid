package com.ilbesculpi.covidtracker.persistence

import com.ilbesculpi.covidtracker.http.Covid19Api
import com.ilbesculpi.covidtracker.http.SummaryResponse
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SummaryRepository() : SummaryService {

    private lateinit var api: Covid19Api

    init {
        initApiService()
    }

    private fun initApiService() {
        val retrofit = Retrofit.Builder()
                .baseUrl(Covid19Api.BASE_URL)
                .addConverterFactory(
                        GsonConverterFactory.create()
                )
                .build()
        this.api = retrofit.create(Covid19Api::class.java)
    }

    override fun fetchSummary(): Call<SummaryResponse> {
        return api.getGlobalSummary()
    }

}