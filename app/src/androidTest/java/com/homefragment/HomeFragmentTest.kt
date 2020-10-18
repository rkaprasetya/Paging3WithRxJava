package com.homefragment

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.example.tandemtest.R
import com.example.tandemtest.presentation.home.HomeActivity
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okhttp3.mockwebserver.RecordedRequest
import okio.buffer
import okio.source
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class HomeFragmentTest {
    @JvmField
    @Rule
    var mActivityRule: ActivityTestRule<HomeActivity> =
        ActivityTestRule(HomeActivity::class.java)
    private var mockWebSerVer = MockWebServer()

    @Before
    fun setup() {
        mockWebSerVer.start(8080)
        ActivityScenario.launch(HomeActivity::class.java)
    }

    @After
    fun stopService() {
        mockWebSerVer.shutdown()
    }

    @Test
    fun activityLaunchSuccessfully() {
        ActivityScenario.launch(HomeActivity::class.java)
    }

    @Test
    fun homeFragment_success_recyclerViewIsShowed() {
        mockWebSerVer.dispatcher = SuccessDispatcher()
        Espresso.onView(withId(R.id.recycler_profile))
            .check(matches(isDisplayed()))
    }

    @Test
    fun homeFragment_error_buttonRetryisShowed() {
        mockWebSerVer.dispatcher = ErrorDispatcher()
        Espresso.onView(withId(R.id.button_retry_home))
            .check(matches(isDisplayed()))
    }

    inner class SuccessDispatcher :
        Dispatcher() {

        private fun setResponseSuccess(
            fileName: String,
            headers: Map<String, String> = emptyMap()
        ): String {
            val inputStream = javaClass.classLoader?.getResourceAsStream("api-response/$fileName")
            val source = inputStream?.source()?.buffer()
            val mockResponse = MockResponse()
            for ((key, value) in headers) {
                mockResponse.addHeader(key, value)
            }
            return source?.readString(Charsets.UTF_8) ?: ""
        }

        override fun dispatch(request: RecordedRequest): MockResponse {
            return MockResponse().setResponseCode(200)
                .setBody(setResponseSuccess("repo_list_response.json"))
        }
    }

    inner class ErrorDispatcher :
        Dispatcher() {
        override fun dispatch(request: RecordedRequest): MockResponse {
            return MockResponse().setResponseCode(404)
        }
    }
}