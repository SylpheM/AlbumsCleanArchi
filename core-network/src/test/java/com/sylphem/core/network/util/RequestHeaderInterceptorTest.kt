package com.sylphem.core.network.util

import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.http.GET


class RequestHeaderInterceptorTest {
    private val mockWebServer = MockWebServer()

    @Test
    fun interceptorAddsHeader() {
        //Given
        val client = OkHttpClient.Builder()
            .addInterceptor(RequestHeaderInterceptor("name", "value"))
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .client(client)
            .build()

        val api = retrofit.create(TestApi::class.java)
        val successResponse = MockResponse()
        mockWebServer.enqueue(successResponse)

        //When
        api.test().execute()

        //Then
        val request = mockWebServer.takeRequest()
        val header = request.getHeader("name")
        assertThat(header, `is`("value"))

        mockWebServer.shutdown()
    }

    private interface TestApi {
        @GET("/test")
        fun test(): Call<Void>
    }
}