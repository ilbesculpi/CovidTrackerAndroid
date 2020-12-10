package com.ilbesculpi.covidtracker.persistence

import com.ilbesculpi.covidtracker.http.Covid19Api
import com.ilbesculpi.covidtracker.http.SummaryResponse
import retrofit2.Call

class SummaryRepository(private val api: Covid19Api) : SummaryService {

    override fun fetchSummary(): Call<SummaryResponse> {
        return api.getGlobalSummary()
    }

}