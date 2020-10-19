package com.example.tandemtest.domain.mapper

import com.example.tandemtest.data.model.Profile
import com.example.tandemtest.data.model.ResponseItem

object CommunityMapper {
    fun convertToProfile(list: List<ResponseItem?>?): MutableList<Profile> {
        val newList = mutableListOf<Profile>()
        list?.forEach {
            newList.add(
                Profile(
                    firstName = it?.firstName,
                    pictureUrl = it?.pictureUrl,
                    learns = it?.learns,
                    topic = it?.topic,
                    referenceCnt = it?.referenceCnt,
                    natives = it?.natives
                )
            )
        }
        return newList
    }
}