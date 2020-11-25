package com.ilbesculpi.covidtracker.utils

import java.text.NumberFormat
import java.util.*

fun Int.formatThousands() : String {
    return NumberFormat.getNumberInstance(Locale.US).format(this)
}
