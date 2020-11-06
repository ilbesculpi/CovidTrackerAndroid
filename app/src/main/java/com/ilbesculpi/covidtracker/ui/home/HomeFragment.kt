package com.ilbesculpi.covidtracker.ui.home

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.ilbesculpi.covidtracker.R
import com.ilbesculpi.covidtracker.ui.controls.StatsView

class HomeFragment : Fragment() {

    /// region - Properties
    private lateinit var newConfirmedView: StatsView
    private lateinit var newDeathsView: StatsView
    private lateinit var newRecoveredView: StatsView
    private lateinit var totalConfirmedView: StatsView
    private lateinit var totalDeathsView: StatsView
    private lateinit var totalRecoveredView: StatsView
    /// endregion

    companion object {
        fun newInstance() = HomeFragment()
    }

    private lateinit var viewModel: HomeViewModel

    /// region - View Lifecycle

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.home_fragment, container, false)
        newConfirmedView = view.findViewById(R.id.stats_new_confirmed)
        newDeathsView = view.findViewById(R.id.stats_new_deaths)
        newRecoveredView = view.findViewById(R.id.stats_new_recovered)
        totalConfirmedView = view.findViewById(R.id.stats_total_confirmed)
        totalDeathsView = view.findViewById(R.id.stats_total_deaths)
        totalRecoveredView = view.findViewById(R.id.stats_total_recovered)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        viewModel.fetchSummary()
        viewModel.globalSummary.observe(this, {
            newConfirmedView.count = it.newConfirmed.toString()
            newDeathsView.count = it.newDeaths.toString()
            newRecoveredView.count = it.newRecovered.toString()
            totalConfirmedView.count = it.totalConfirmed.toString()
            totalDeathsView.count = it.totalDeaths.toString()
            totalRecoveredView.count = it.totalRecovered.toString()
        })
    }

    /// endregion

}