/**
 * Color extension utils.
 */
package com.ilbesculpi.covidtracker.utils

import android.graphics.Color
import android.graphics.drawable.ColorDrawable

/**
 * Generates a new Color by applying a factor to a given base color.
 */
fun ColorDrawable.darken(factor: Float) : ColorDrawable {
    var color = this.color
    val hsv = FloatArray(3)
    Color.colorToHSV(color, hsv)
    //hsv[0] *= factor
    hsv[1] *= factor
    hsv[2] *= factor // value component
    val darkened = android.graphics.Color.HSVToColor(hsv)
    return android.graphics.drawable.ColorDrawable(darkened)
}
