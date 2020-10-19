package com.example.tandemtest.data.api

import com.example.tandemtest.data.model.CommunityResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("api/community_{page}.json")
    fun getCommunity(@Path("page")page:Int): Single<CommunityResponse>
}