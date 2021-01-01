package com.ilbesculpi.covidtracker.ui.home

import android.util.Log
import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.ilbesculpi.covidtracker.http.SummaryResponse
import com.ilbesculpi.covidtracker.models.CountrySummary
import com.ilbesculpi.covidtracker.models.GlobalSummary
import com.ilbesculpi.covidtracker.repository.SummaryRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

const val TAG = "CovidTrackerLog"

class HomeViewModel @ViewModelInject constructor(
    private val service: SummaryRepository
    ) : ViewModel() {

    /// region Properties
    var loading: MutableLiveData<Boolean> = MutableLiveData()
    var errorMessage: MutableLiveData<String> = MutableLiveData()
    var globalSummary: MutableLiveData<GlobalSummary> = MutableLiveData()
    var countrySummary: MutableLiveData<List<CountrySummary>> = MutableLiveData()
    /// endregion

    init {
        //fetchSummary()
    }

    fun fetchSummary() {

        loading.postValue(true);

        service.fetchSummary().enqueue(object : Callback<SummaryResponse> {

            override fun onResponse(call: Call<SummaryResponse>, response: Response<SummaryResponse>) {

                if( !response.isSuccessful ) {
                    loading.postValue(false);
                    displayError(response.message());
                    return;
                }

                if( response.body() != null ) {
                    loading.postValue(false);
                    globalSummary.postValue( response.body()!!.Global );
                    countrySummary.postValue( response.body()!!.Countries );
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