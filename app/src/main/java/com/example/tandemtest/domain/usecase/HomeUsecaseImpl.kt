package com.example.tandemtest.domain.usecase

import androidx.paging.PagingData
import com.example.tandemtest.data.model.Profile
import com.example.tandemtest.domain.repository.CommunityRepository
import io.reactivex.Flowable
import javax.inject.Inject
import javax.inject.Singleton

class HomeUsecase @Inject constructor(private val communityRepository: CommunityRepository) {
    fun getProfiles(): Flowable<PagingData<Profile>> {
        return communityRepository.getProfile()
    }
}