package com.ilbesculpi.covidtracker.repository

import com.ilbesculpi.covidtracker.http.Covid19Api
import com.ilbesculpi.covidtracker.http.SummaryResponse
import retrofit2.Call
import javax.inject.Inject

class SummaryService @Inject constructor(private var api: Covid19Api) : SummaryRepository {

    override fun fetchSummary(): Call<SummaryResponse> {
        return api.getGlobalSummary()
    }

}