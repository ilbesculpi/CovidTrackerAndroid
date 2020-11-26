package com.ilbesculpi.covidtracker.ui.countries

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ilbesculpi.covidtracker.http.Covid19Api
import com.ilbesculpi.covidtracker.http.SummaryResponse
import com.ilbesculpi.covidtracker.models.Country
import com.ilbesculpi.covidtracker.models.CountrySummary
import com.ilbesculpi.covidtracker.models.GlobalSummary
import com.ilbesculpi.covidtracker.ui.home.TAG
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import kotlin.Comparator

class CountryListViewModel : ViewModel() {

    /// region Properties
    lateinit var service: Covid19Api
    var loading: MutableLiveData<Boolean> = MutableLiveData()
    var countryList: MutableLiveData<List<CountrySummary>> = MutableLiveData()
    /// endregion

    init {
        initApiService()
        fetchSummary()
    }

    private fun initApiService() {
        val retrofit = Retrofit.Builder()
            .baseUrl(Covid19Api.BASE_URL)
            .addConverterFactory(
                GsonConverterFactory.create()
            )
            .build()
        this.service = retrofit.create(Covid19Api::class.java)
    }

    private fun fetchSummary() {

        println("fetchSummary()")

        loading.postValue(true)

        service.getGlobalSummary().enqueue(object : Callback<SummaryResponse> {

            override fun onResponse(call: Call<SummaryResponse>, response: Response<SummaryResponse>) {

                if( !response.isSuccessful ) {
                    displayError(response.message())
                    loading.postValue(false)
                    return
                }

                if( response.body() != null ) {

                    val summary = response.body()!!
                    val items = summary.Countries
                    Collections.sort(items, object: Comparator<CountrySummary> {
                        override fun compare(o1: CountrySummary?, o2: CountrySummary?): Int {
                            if( o1 != null && o2 != null ) {
                                return o1.country.compareTo(o2.country)
                            }
                            if( o1 != null ) {
                                return 1
                            }
                            return -1
                        }
                    })
                    countryList.postValue( items )
                    loading.postValue(false);
                }

            }

            override fun onFailure(call: Call<SummaryResponse>, t: Throwable) {
                loading.postValue(false);
                displayError(t.localizedMessage);
            }

        })

    }

    fun displayError(message: String) {
        Log.e(TAG, "showError: $message");
    }

}