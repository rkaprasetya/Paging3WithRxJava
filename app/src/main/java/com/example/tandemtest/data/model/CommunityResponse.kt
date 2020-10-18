package com.example.tandemtest.data.model

import com.squareup.moshi.Json

data class CommunityResponse(

    @field:Json(name = "response")
    val response: List<ResponseItem?>? = null,

    @field:Json(name = "errorCode")
    val errorCode: Any? = null,

    @field:Json(name = "type")
    val type: String? = null
)

data class ResponseItem(

    @field:Json(name = "firstName")
    val firstName: String? = null,

    @field:Json(name = "referenceCnt")
    val referenceCnt: Int? = null,

    @field:Json(name = "pictureUrl")
    val pictureUrl: String? = null,

    @field:Json(name = "learns")
    val learns: List<String?>? = null,

    @field:Json(name = "topic")
    val topic: String? = null,

    @field:Json(name = "natives")
    val natives: List<String?>? = null
)
