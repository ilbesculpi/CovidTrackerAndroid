package com.ilbesculpi.covidtracker.persistence

import com.ilbesculpi.covidtracker.http.Covid19Api
import com.ilbesculpi.covidtracker.http.SummaryResponse
import com.ilbesculpi.covidtracker.models.Country
import org.junit.Assert.*
import org.junit.Test
import retrofit2.Call
import retrofit2.Response



class SummaryRepositoryTest {

    lateinit var repository: SummaryRepository

    fun createRepository() {

    }

    @Test
    fun testFetchSummary() {
        val response = repository.fetchSummary()
        assertNotNull(response)
    }
}