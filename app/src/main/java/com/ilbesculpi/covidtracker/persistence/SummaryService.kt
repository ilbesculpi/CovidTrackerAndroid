package com.ilbesculpi.covidtracker.persistence

import com.ilbesculpi.covidtracker.http.SummaryResponse
import retrofit2.Call

interface SummaryService {

    fun fetchSummary() : Call<SummaryResponse>

}