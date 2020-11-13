package com.ilbesculpi.covidtracker.ui.countries

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.ilbesculpi.covidtracker.R
import com.ilbesculpi.covidtracker.databinding.CountryListFragmentBinding

class CountryListFragment : Fragment() {

    /// region - Properties
    private lateinit var viewModel: CountryListViewModel
    private lateinit var binding: CountryListFragmentBinding
    private val navController
        get() = binding.root.findNavController()
    private lateinit var adapter: CountryListAdapter
    /// endregion

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = CountryListFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {

        super.onActivityCreated(savedInstanceState)
        initListView()
        viewModel = ViewModelProvider(this).get(CountryListViewModel::class.java)

        viewModel.loading.observe(viewLifecycleOwner, {
            binding.loadingIndicator.visibility = if( it ) View.VISIBLE else View.INVISIBLE
        })

        viewModel.countryList.observe(viewLifecycleOwner, {
            adapter = CountryListAdapter(it, requireContext())
            binding.listView.adapter = adapter
        })

    }

    fun initListView() {
        binding.listView.setHasFixedSize(true)
        val linearLayoutManager = LinearLayoutManager(context)
        binding.listView.layoutManager = linearLayoutManager
    }

}