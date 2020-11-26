package com.ilbesculpi.covidtracker.ui.countries

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ilbesculpi.covidtracker.R
import com.ilbesculpi.covidtracker.models.Country
import com.ilbesculpi.covidtracker.models.CountrySummary
import com.ilbesculpi.covidtracker.utils.formatThousands

class CountryListAdapter(private val countries: List<CountrySummary>, val context: Context)
    : RecyclerView.Adapter<CountryListAdapter.ViewHolder>() {

    /// region ViewHolder
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var flagIcon: ImageView = itemView.findViewById(R.id.flag)
        var labelCountry: TextView = itemView.findViewById(R.id.labelCountry)
        var labelConfirmed: TextView = itemView.findViewById(R.id.labelConfirmed)
        var labelDeaths: TextView = itemView.findViewById(R.id.labelDeaths)
        var labelRecovered: TextView = itemView.findViewById(R.id.labelRecovered)
    }
    /// endregion


    /// region Properties
    lateinit var listener: OnClickListener
    ///

    interface OnClickListener {
        fun onItemClick(v: View, position: Int, item: CountrySummary)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.row_country_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val country = countries[position]

        val resId = context.resources.getIdentifier(
            country.flagIcon,
            "drawable",
            context.packageName
        )
        holder.flagIcon.setImageResource(resId)
        holder.labelCountry.text = country.country
        holder.labelConfirmed.text = country.totalConfirmed.formatThousands()
        holder.labelDeaths.text = country.totalDeaths.formatThousands()
        holder.labelRecovered.text = country.totalRecovered.formatThousands()

        holder.itemView.setOnClickListener {
            listener.onItemClick(holder.itemView, position, country)
        }
    }

    override fun getItemCount(): Int {
        return countries.size
    }

}