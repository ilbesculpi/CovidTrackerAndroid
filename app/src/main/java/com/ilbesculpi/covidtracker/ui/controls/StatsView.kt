package com.ilbesculpi.covidtracker.ui.controls

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.ilbesculpi.covidtracker.R
import com.ilbesculpi.covidtracker.utils.darken

@SuppressLint("ResourceAsColor")
class StatsView(context: Context, attrs: AttributeSet): LinearLayout(context, attrs) {

    var title: String
        get() = labelTitle.text.toString()
        set(value) {
            labelTitle.text = value
            invalidate()
            requestLayout()
        }

    var count: String
        get() = labelCount.text.toString()
        set(value) {
            labelCount.text = value
            invalidate()
            requestLayout()
        }

    private lateinit var labelTitle: TextView
    private lateinit var labelCount: TextView

    init {
        
        initializeViews(context)

        // read and apply custom view attributes (title/count)
        context.theme.obtainStyledAttributes(attrs, R.styleable.StatsView, 0, 0).apply {
            try {
                val titleText = getText(R.styleable.StatsView_title).toString()
                val countText = getText(R.styleable.StatsView_value).toString()
                val color = getColorStateList(R.styleable.StatsView_accent)
                display(titleText, countText)
                labelCount.setTextColor(color)
            }
            finally {
                recycle()
            }
        }
    }

    private fun initializeViews(context: Context) {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater.inflate(R.layout.stats_view, this, true)
        labelTitle = view.findViewById(R.id.labelTitle)
        labelCount = view.findViewById(R.id.labelCount)
        //footerView = view.findViewById(R.id.footer)
        // set footer background color
        //val footerColor = (view.background as ColorDrawable).darken(2.5f)
        //footerView.background = footerColor
    }

    private fun display(title: String, count: String) {
        this.title = title
        this.count = count
    }

}