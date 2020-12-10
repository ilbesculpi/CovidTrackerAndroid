package com.ilbesculpi.covidtracker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import com.ilbesculpi.covidtracker.databinding.ActivityMainBinding
import com.ilbesculpi.covidtracker.databinding.HomeFragmentBinding
import com.ilbesculpi.covidtracker.ui.countries.CountryListFragment
import com.ilbesculpi.covidtracker.ui.home.HomeFragment

class MainActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        val navbar = binding.navbar
        navbar.setOnNavigationItemSelectedListener(this)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when( item.itemId ) {
            R.id.action_home -> {
                displayHome()
            }
            R.id.action_country_list -> {
                displayCountryList()
            }
        }
        return true
    }

    private fun displayHome() {
        val fragment = HomeFragment()
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment, fragment)
            .commit()
    }

    private fun displayCountryList() {
        val fragment = CountryListFragment()
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment, fragment)
            .commit()
    }

}