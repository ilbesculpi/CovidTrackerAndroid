package com.ilbesculpi.covidtracker.models

import java.util.*

data class CountrySummary(
        val country: String,
        val countryCode: String,
        val slug: String,
        val newConfirmed: Int,
        val totalConfirmed: Int,
        val newDeaths: Int,
        val totalDeaths: Int,
        val newRecovered: Int,
        val totalRecovered: Int,
        val date: Date
) {
}