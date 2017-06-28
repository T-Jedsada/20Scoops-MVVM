package com.tweentyscoops.mvvm

import android.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import com.tweentyscoops.mvvm.service.model.MovieDao
import com.tweentyscoops.mvvm.service.repository.MovieApi
import com.tweentyscoops.mvvm.ui.template.CustomViewModel
import io.reactivex.Observable
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.ArgumentMatchers.anyString
import org.mockito.MockitoAnnotations
import retrofit2.Response

@RunWith(JUnit4::class)
class CustomViewModelTest {

    @Rule @JvmField val rxSchedulerRule = RxSchedulersOverrideRule()
    @Rule @JvmField val instantTaskExecutorRule = InstantTaskExecutorRule()

    private var mockAPIs = mock<MovieApi> {}
    private val jsonUtil = JsonMockUtility()
    private val customViewModel = CustomViewModel(mockAPIs)

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun requestMovieSuccess() {
        val mockResult = jsonUtil.getJsonToMock("movie_success.json", MovieDao::class.java)
        assertEquals(mockResult.result.size, 20)
        val mockResponse = Response.success(mockResult)
        val mockObservable = Observable.just(mockResponse)
        whenever(mockAPIs.getMovie(anyString(), anyInt())).thenReturn(mockObservable)
        customViewModel.getMovie("popularity.desc")
        val testObserver = mockObservable.test()
        testObserver.awaitTerminalEvent()
        testObserver.assertNoErrors()
        testObserver.assertComplete()
        testObserver.assertValueCount(1)
        testObserver.assertResult(mockResponse)
        testObserver.assertValue { return@assertValue it.body() == mockResponse.body() }
    }

    @Test
    fun requestMovieError() {
        val throwable = Throwable("error")
        val mockObservable = Observable.error<Response<MovieDao>>(throwable)
        whenever(mockAPIs.getMovie(anyString(), anyInt())).thenReturn(mockObservable)
        customViewModel.getMovie("sort_by")
        val testObserver = mockObservable.test()
        testObserver.awaitTerminalEvent()
        testObserver.assertSubscribed()
        testObserver.assertNotComplete()
        testObserver.assertError(throwable)
    }

    @Test
    fun requestMovieEmpty() {
        val mockResult = jsonUtil.getJsonToMock("movie_empty.json", MovieDao::class.java)
        assertEquals(mockResult.result.size, 0)
        val mockResponse = Response.success(mockResult)
        val mockObservable = Observable.just(mockResponse)
        whenever(mockAPIs.getMovie(anyString(), anyInt())).thenReturn(mockObservable)
        customViewModel.getMovie("sort_by")
        val testObserver = mockObservable.test()
        testObserver.awaitTerminalEvent()
        testObserver.assertNoErrors()
        testObserver.assertComplete()
        testObserver.assertResult(mockResponse)
    }

    @Test
    fun requestMovieInvalidAPI() {
        val mockResult = jsonUtil.getJsonToMock("movie_invalid_key.json", MovieDao::class.java)
        val mockResponse = Response.success(mockResult)
        val mockObservable = Observable.just(mockResponse)
        whenever(mockAPIs.getMovie(anyString(), anyInt())).thenReturn(mockObservable)
        customViewModel.getMovie("sort_by")
        val testObserver = mockObservable.test()
        testObserver.awaitTerminalEvent()
        testObserver.assertNoErrors()
        testObserver.assertComplete()
        testObserver.assertResult(mockResponse)
    }
}