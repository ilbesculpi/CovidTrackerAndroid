package com.ilbesculpi.covidtracker.app

import com.ilbesculpi.covidtracker.http.Covid19Api
import com.ilbesculpi.covidtracker.repository.SummaryRepository
import com.ilbesculpi.covidtracker.repository.SummaryService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.ApplicationComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(ApplicationComponent::class)
object MainModule {

    @Provides
    fun bindApi() : Covid19Api {
        val retrofit = Retrofit.Builder()
            .baseUrl(Covid19Api.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(Covid19Api::class.java)
    }

    @Provides
    fun summaryRepository(api: Covid19Api) : SummaryRepository {
        return SummaryService(api)
    }

}