package com.ilbesculpi.covidtracker.repository

import com.ilbesculpi.covidtracker.http.SummaryResponse
import retrofit2.Call

interface SummaryRepository {

    fun fetchSummary() : Call<SummaryResponse>

}