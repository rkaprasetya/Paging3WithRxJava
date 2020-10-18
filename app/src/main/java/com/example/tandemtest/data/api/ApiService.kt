package com.example.tandemtest.data.api

import com.example.tandemtest.data.model.CommunityResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("api/community_{page}.json")
    suspend fun getCommunity(@Path("page")page:Int):CommunityResponse
}