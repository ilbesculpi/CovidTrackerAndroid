package com.ilbesculpi.covidtracker.repository

import org.junit.Assert.*
import org.junit.Test


class SummaryServiceTest {

    lateinit var repository: SummaryService

    fun createRepository() {

    }

    @Test
    fun testFetchSummary() {
        val response = repository.fetchSummary()
        assertNotNull(response)
    }
}