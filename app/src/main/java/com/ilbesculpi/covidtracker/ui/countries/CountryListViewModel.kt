package com.ilbesculpi.covidtracker.ui.countries

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ilbesculpi.covidtracker.http.Covid19Api
import com.ilbesculpi.covidtracker.models.Country
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
    var countryList: MutableLiveData<List<Country>> = MutableLiveData()
    /// endregion

    init {
        initApiService()
        fetchCountryList()
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

    private fun fetchCountryList() {

        println("fetchCountryList()");

        loading.postValue(true);

        service.getCountryList().enqueue(object : Callback<List<Country>> {

            override fun onResponse(call: Call<List<Country>>, response: Response<List<Country>>) {

                if( !response.isSuccessful ) {
                    displayError(response.message())
                    loading.postValue(false)
                    return
                }

                if( response.body() != null ) {

                    var items = response.body()!!
                    Collections.sort(items, object: Comparator<Country> {
                        override fun compare(o1: Country?, o2: Country?): Int {
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

            override fun onFailure(call: Call<List<Country>>, t: Throwable) {
                loading.postValue(false);
                displayError(t.localizedMessage);
            }

        })

    }

    fun displayError(message: String) {
        Log.e(TAG, "showError: $message");
    }

}