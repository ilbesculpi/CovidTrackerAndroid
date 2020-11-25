package com.ilbesculpi.covidtracker.models

import com.google.gson.annotations.SerializedName
import java.util.*

data class CountrySummary(
        @SerializedName("Country")
        val country: String,
        @SerializedName("CountryCode")
        val countryCode: String,
        @SerializedName("Slug")
        val slug: String,
        @SerializedName("NewConfirmed")
        val newConfirmed: Int,
        @SerializedName("TotalConfirmed")
        val totalConfirmed: Int,
        @SerializedName("NewDeaths")
        val newDeaths: Int,
        @SerializedName("TotalDeaths")
        val totalDeaths: Int,
        @SerializedName("NewRecovered")
        val newRecovered: Int,
        @SerializedName("TotalRecovered")
        val totalRecovered: Int,
        @SerializedName("Date")
        val date: Date
)