package com.ilbesculpi.covidtracker.ui.controls

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.ilbesculpi.covidtracker.R

class StatsView(context: Context, attrs: AttributeSet): LinearLayout(context, attrs) {

    var title: String
        get() = labelTitle.text.toString()
        set(value) { labelTitle.text = value }

    var count: String
        get() = labelCount.text.toString()
        set(value) { labelCount.text = value }

    private lateinit var labelTitle: TextView
    private lateinit var labelCount: TextView
    private lateinit var footerView: ViewGroup

    init {
        initializeViews(context)
        val arr = context.obtainStyledAttributes(attrs, R.styleable.StatsView)
        val titleText = arr.getText(R.styleable.StatsView_text).toString()
        val countText = arr.getText(R.styleable.StatsView_value).toString()
        display(titleText, countText)
        arr.recycle()
    }

    private fun initializeViews(context: Context) {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater.inflate(R.layout.stats_view, this, true)
        labelTitle = view.findViewById(R.id.labelTitle)
        labelCount = view.findViewById(R.id.labelCount)
        footerView = view.findViewById(R.id.footer)
        // set background color
        val bgColor = (view.background as ColorDrawable).color
        val footerColor = darkenColor(bgColor, 2.5f)
        footerView.background = ColorDrawable(footerColor)
    }

    private fun display(title: String, count: String) {
        this.title = title
        this.count = count
    }

    private fun darkenColor(color: Int, factor: Float): Int {
        var color = color
        val hsv = FloatArray(3)
        Color.colorToHSV(color, hsv)
        //hsv[0] *= factor
        hsv[1] *= factor
        hsv[2] *= factor // value component
        color = Color.HSVToColor(hsv)
        return color
    }

}