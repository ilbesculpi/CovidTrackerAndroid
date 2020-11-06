package com.ilbesculpi.covidtracker.http

import com.ilbesculpi.covidtracker.models.CountrySummary
import com.ilbesculpi.covidtracker.models.GlobalSummary
import retrofit2.Call
import retrofit2.http.GET

data class SummaryResponse (
    val Global: GlobalSummary,
    val Countries: List<CountrySummary>
)

interface Covid19Api {

    companion object {
        const val BASE_URL: String = "https://api.covid19api.com"
    }

    @GET("/summary")
    fun getGlobalSummary() : Call<SummaryResponse>

}