package com.ilbesculpi.covidtracker.ui.countries

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ilbesculpi.covidtracker.R

class CountryStatsFragment : Fragment() {

    companion object {
        fun newInstance() = CountryStatsFragment()
    }

    private lateinit var viewModel: CountryStatsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.country_stats_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(CountryStatsViewModel::class.java)
        // TODO: Use the ViewModel
    }

}