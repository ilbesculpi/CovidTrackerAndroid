package com.ilbesculpi.covidtracker.ui.home

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ilbesculpi.covidtracker.http.Covid19Api
import com.ilbesculpi.covidtracker.http.SummaryResponse
import com.ilbesculpi.covidtracker.models.CountrySummary
import com.ilbesculpi.covidtracker.models.GlobalSummary
import com.ilbesculpi.covidtracker.persistence.SummaryRepository
import com.ilbesculpi.covidtracker.persistence.SummaryService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val TAG = "CovidTrackerLog"

class HomeViewModel : ViewModel() {

    /// region Properties
    var service: SummaryService = SummaryRepository()
    var loading: MutableLiveData<Boolean> = MutableLiveData()
    var errorMessage: MutableLiveData<String> = MutableLiveData()
    var globalSummary: MutableLiveData<GlobalSummary> = MutableLiveData()
    var countrySummary: MutableLiveData<List<CountrySummary>> = MutableLiveData()
    /// endregion

    init {
        fetchSummary()
    }



    fun fetchSummary() {

        println("fetchSummary()");

        loading.postValue(true);

        service.fetchSummary().enqueue(object : Callback<SummaryResponse> {

            override fun onResponse(call: Call<SummaryResponse>, response: Response<SummaryResponse>) {

                if( !response.isSuccessful ) {
                    loading.postValue(false);
                    displayError(response.message());
                    return;
                }

                if( response.body() != null ) {
                    globalSummary.postValue( response.body()!!.Global );
                    countrySummary.postValue( response.body()!!.Countries );
                    loading.postValue(false);
                }

            }

            override fun onFailure(call: Call<SummaryResponse>, t: Throwable) {
                loading.postValue(false);
                displayError(t.localizedMessage);
            }

        });
    }

    private fun displayError(message: String) {
        Log.e(TAG, "fetchSummary error: $message");
        errorMessage.postValue(message);
    }

}