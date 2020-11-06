package com.ilbesculpi.covidtracker.ui.controls

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.view.get
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

    init {
        initializeViews(context)
        val arr = context.obtainStyledAttributes(attrs, R.styleable.StatsView)
        val titleText = arr.getText(R.styleable.StatsView_text).toString()
        val countText = arr.getText(R.styleable.StatsView_value).toString()
        title = titleText
        count = countText
        arr.recycle()
    }

    private fun initializeViews(context: Context) {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater.inflate(R.layout.stats_view, this, true)
        labelTitle = view.findViewById(R.id.labelTitle)
        labelCount = view.findViewById(R.id.labelCount)
    }

}