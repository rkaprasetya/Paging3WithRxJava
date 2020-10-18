package com.example.network

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.tandemtest.data.api.ApiService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import org.junit.Assert.*
import okio.buffer
import okio.source
import org.junit.Test
import org.hamcrest.CoreMatchers.`is`

@RunWith(JUnit4::class)
class ApiServiceTest {
    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()
    private lateinit var SUT: ApiService
    private lateinit var mockWebSerVer: MockWebServer
    @Before
    fun createService() {
        mockWebSerVer = MockWebServer()
        mockWebSerVer.start()
        val client = OkHttpClient.Builder()
            .readTimeout(10, TimeUnit.SECONDS)
            .connectTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.SECONDS)
            .build()
        SUT = Retrofit.Builder()
            .baseUrl(mockWebSerVer.url(""))
            .addConverterFactory(
                MoshiConverterFactory.create(
                Moshi.Builder()
                .add(KotlinJsonAdapterFactory())
                .build()))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(client)
            .build()
            .create(ApiService::class.java)
    }
    @After
    fun stopService(){
        mockWebSerVer.shutdown()
    }

    @Test
    fun loadCommunity_passCorrectPath(){
        runBlocking {
            //arrange
            setResponseSuccess("api_community_response.json")
            //act
            val resultResponse = SUT.getCommunity(1)
            val request = mockWebSerVer.takeRequest()
            //assert
            assertNotNull(resultResponse)
            assertThat(request.path, `is`("/api/community_1.json"))
        }
    }
    @Test
    fun loadCommunity_success_getCorrectDataSize(){
        runBlocking {
            //arrange
            setResponseSuccess("api_community_response.json")
            //act
            val resultResponse = SUT.getCommunity(1)
            val dataResponse = resultResponse.response
            //assert
            assertThat(20,`is`(dataResponse?.size))
        }
    }
    @Test
    fun loadCommunity_success_getCorrectResponse(){
        runBlocking {
            //arrange
            setResponseSuccess("api_community_response.json")
            //act
            val resultResponse = SUT.getCommunity(1)
            val dataResponse = resultResponse.response
            //assert
            assertThat("Tobi",`is`(dataResponse!![0]!!.firstName))
        }
    }
    private fun setResponseSuccess(fileName: String, headers: Map<String, String> = emptyMap()) {
        val inputStream = javaClass.classLoader?.getResourceAsStream("api-response/$fileName")
        val source = inputStream?.source()?.buffer()
        val mockResponse = MockResponse()
        for ((key, value) in headers) {
            mockResponse.addHeader(key, value)
        }
        mockWebSerVer.enqueue(mockResponse.setBody(source!!.readString(Charsets.UTF_8)))
    }

}