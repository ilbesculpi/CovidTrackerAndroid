package com.ilbesculpi.covidtracker.ui.countries

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ilbesculpi.covidtracker.R
import com.ilbesculpi.covidtracker.models.Country

class CountryListAdapter(val countries: List<Country>, val context: Context)
    : RecyclerView.Adapter<CountryListAdapter.ViewHolder>() {

    /// region ViewHolder
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var textView: TextView = itemView.findViewById(R.id.textView)
    }
    /// endregion

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.row_country_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val country = countries[position]
        holder.textView.text = country.country
    }

    override fun getItemCount(): Int {
        return countries.size
    }

}