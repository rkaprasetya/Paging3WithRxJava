package com.example.tandemtest.domain.mapper

import com.example.tandemtest.data.model.Profile
import com.example.tandemtest.data.model.ResponseItem

object CommunityMapper {
    fun convertToProfile(item: ResponseItem?): Profile {
        return Profile(
            firstName = item?.firstName,
            pictureUrl = item?.pictureUrl,
            learns = item?.learns,
            topic = item?.topic,
            referenceCnt = item?.referenceCnt,
            natives = item?.natives
        )
    }
}