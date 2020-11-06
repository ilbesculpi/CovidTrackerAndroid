package com.ilbesculpi.covidtracker.ui.home

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ilbesculpi.covidtracker.http.Covid19Api
import com.ilbesculpi.covidtracker.http.SummaryResponse
import com.ilbesculpi.covidtracker.models.CountrySummary
import com.ilbesculpi.covidtracker.models.GlobalSummary
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val TAG = "CovidTrackerLog"

class HomeViewModel : ViewModel() {

    lateinit var service: Covid19Api
    var globalSummary: MutableLiveData<GlobalSummary> = MutableLiveData()
    var countrySummary: MutableLiveData<List<CountrySummary>> = MutableLiveData()

    init {
        initApiService()
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

    fun fetchSummary() {

        service.getGlobalSummary().enqueue(object : Callback<SummaryResponse> {

            override fun onResponse(call: Call<SummaryResponse>, response: Response<SummaryResponse>) {

                if( !response.isSuccessful ) {
                    showError(response.message())
                    return
                }

                if( response.body() != null ) {
                    globalSummary.postValue( response.body()!!.Global )
                    countrySummary.postValue( response.body()!!.Countries )
                }

            }

            override fun onFailure(call: Call<SummaryResponse>, t: Throwable) {
                showError(t.localizedMessage)
            }

        })
    }

    fun showError(message: String) {
        Log.e(TAG, "showError: ${message}")
    }
}