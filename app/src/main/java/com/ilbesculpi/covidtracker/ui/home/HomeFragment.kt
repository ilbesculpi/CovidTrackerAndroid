package com.ilbesculpi.covidtracker.ui.home

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.ilbesculpi.covidtracker.R
import com.ilbesculpi.covidtracker.databinding.HomeFragmentBinding
import com.ilbesculpi.covidtracker.ui.controls.StatsView
import com.ilbesculpi.covidtracker.utils.formatThousands

class HomeFragment : Fragment() {

    /// region - Properties
    private lateinit var binding: HomeFragmentBinding
    private lateinit var viewModel: HomeViewModel
    /// endregion

    companion object {
        fun newInstance() = HomeFragment()
    }


    /// region - View Lifecycle

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = HomeFragmentBinding.inflate(inflater, container, false);
        val view = binding.root;
        return view;
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {

        super.onActivityCreated(savedInstanceState);
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java);

        viewModel.globalSummary.observe(this, {
            binding.statsNewConfirmed.count = it.newConfirmed.formatThousands()
            binding.statsNewDeaths.count = it.newDeaths.formatThousands()
            binding.statsNewRecovered.count = it.newRecovered.formatThousands()
            binding.statsTotalConfirmed.count = it.totalConfirmed.formatThousands()
            binding.statsTotalDeaths.count = it.totalDeaths.formatThousands()
            binding.statsTotalRecovered.count = it.totalRecovered.formatThousands()
        });

        viewModel.loading.observe(this, {
            binding.loadingIndicator.visibility = if( it ) View.VISIBLE else View.INVISIBLE;
        });

        viewModel.fetchSummary();

        binding.buttonCountries.setOnClickListener { displayCountriesScreen() }
    }

    /// endregion

    fun displayCountriesScreen() {
        // TODO: navigate to countries screen
    }

}