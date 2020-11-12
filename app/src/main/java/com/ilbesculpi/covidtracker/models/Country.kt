package com.ilbesculpi.covidtracker.models

import com.google.gson.annotations.SerializedName

data class Country(
        @SerializedName("NewConfirmed")
        val country: String,
        @SerializedName("Slug")
        val slug: String,
        @SerializedName("ISO2")
        val iso2: String
)
