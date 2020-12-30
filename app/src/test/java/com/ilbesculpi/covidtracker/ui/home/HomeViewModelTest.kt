package com.ilbesculpi.covidtracker.ui.home

import android.provider.ContactsContract.CommonDataKinds.Email
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.ilbesculpi.covidtracker.http.SummaryResponse
import com.ilbesculpi.covidtracker.models.GlobalSummary
import com.ilbesculpi.covidtracker.repository.SummaryRepository
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.ArgumentCaptor
import org.mockito.Captor
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class HomeViewModelTest {


    // JUnit test rule that swaps the background executor
    // used by the architecture components
    // with a different one that executes each task synchronously
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    lateinit var autoCloseable: AutoCloseable

    @Mock
    lateinit var loadingObserver: Observer<Boolean>

    @Mock
    lateinit var summaryObserver: Observer<GlobalSummary>

    @Mock
    lateinit var mockRepository: SummaryRepository

    @Mock
    lateinit var callback: Call<SummaryResponse>

    lateinit var viewModel: HomeViewModel

    @Captor
    lateinit var argsCaptor: ArgumentCaptor<Callback<SummaryResponse>>



    @Before
    fun setup() {

        autoCloseable = MockitoAnnotations.openMocks(this)
        //mockRepository = mock(SummaryRepository::class.java)

        val summary = GlobalSummary(
                0,
                10,
                20,
                30,
                40,
                50)
        val sr = SummaryResponse(summary, listOf())
        val response = Response.success(sr)
        //`when`(callback).thenReturn(response)
        
        `when`(callback.enqueue(argsCaptor.capture())).then {

        }
        `when`(mockRepository.fetchSummary()).thenReturn(callback)
    }

    @After
    fun releaseMocks() {
        autoCloseable.close()
    }


    @Test
    fun testLoadingWhenFetchData() {

        viewModel = HomeViewModel(mockRepository)
        viewModel.loading.observeForever(loadingObserver)

        //verify(loadingObserver).onChanged(false)
        viewModel.fetchSummary()
        verify(loadingObserver).onChanged(true)
        verify(loadingObserver).onChanged(false)
        
    }

    @Test
    fun testFetchSummaryData() {

        viewModel = HomeViewModel(mockRepository)
        viewModel.globalSummary.observeForever(summaryObserver)

        viewModel.fetchSummary()
        val expected = GlobalSummary(
                0,
                10,
                20,
                30,
                40,
                50
        )
        verify(summaryObserver).onChanged(expected)
    }

}