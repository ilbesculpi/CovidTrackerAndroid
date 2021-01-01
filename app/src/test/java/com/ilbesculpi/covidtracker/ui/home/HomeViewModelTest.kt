package com.ilbesculpi.covidtracker.ui.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.ilbesculpi.covidtracker.http.SummaryResponse
import com.ilbesculpi.covidtracker.models.GlobalSummary
import com.ilbesculpi.covidtracker.repository.SummaryRepository
import okhttp3.Request
import okio.Timeout
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.*
import org.mockito.Mockito.*
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

        // Create Repository Stub
        //mockRepository = SummaryRepositoryStub()
        //mockRepository.thenReturn(sr)

        // Create ViewModel
        viewModel = HomeViewModel(mockRepository)
    }

    @After
    fun releaseMocks() {
        autoCloseable.close()
    }

    private fun makeSuccessResponse(summary: GlobalSummary) : Call<SummaryResponse> {

        return object : Call<SummaryResponse> {

            override fun execute(): Response<SummaryResponse> {
                TODO("Not yet implemented")
            }

            override fun enqueue(callback: Callback<SummaryResponse>) {
                val response = SummaryResponse(summary, listOf())
                val rs = Response.success(response)
                callback.onResponse(this, rs)
            }

            override fun isExecuted(): Boolean {
                TODO("Not yet implemented")
            }

            override fun isCanceled(): Boolean {
                TODO("Not yet implemented")
            }

            override fun cancel() {
                TODO("Not yet implemented")
            }

            override fun request(): Request {
                TODO("Not yet implemented")
            }

            override fun timeout(): Timeout {
                TODO("Not yet implemented")
            }

            override fun clone(): Call<SummaryResponse> {
                TODO("Not yet implemented")
            }
        }
    }

    @Test
    fun testLoadingWhenFetchData() {

        val summary = GlobalSummary(
                0,
                10,
                20,
                30,
                40,
                50)
        val callResponse = makeSuccessResponse(summary)

        `when`(mockRepository.fetchSummary()).thenReturn(callResponse)

        viewModel.loading.observeForever(loadingObserver)

        viewModel.fetchSummary()
        verify(loadingObserver).onChanged(true)
        verify(loadingObserver).onChanged(false)
    }

    @Test
    fun testFetchSummaryData() {

        val summary = GlobalSummary(
                0,
                10,
                20,
                30,
                40,
                50)
        val callResponse = makeSuccessResponse(summary)

        `when`(mockRepository.fetchSummary()).thenReturn(callResponse)

        viewModel.globalSummary.observeForever(summaryObserver)

        viewModel.fetchSummary()

        // Expect summary data
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

    @Test
    fun testRepositoryInteractions() {

        val summary = GlobalSummary(
                0,
                10,
                20,
                30,
                40,
                50)
        val callResponse = makeSuccessResponse(summary)

        `when`(mockRepository.fetchSummary()).thenReturn(callResponse)

        // Then: call fetchSummary()
        viewModel.fetchSummary()

        // Expect: summary::fetchSummary() to be called once
        verify(mockRepository, times(1)).fetchSummary()


    }

}