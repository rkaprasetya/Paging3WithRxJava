package com.example.tandemtest.domain.usecase

import androidx.paging.PagingData
import com.example.tandemtest.data.model.Profile
import com.example.tandemtest.domain.repository.CommunityRepository
import io.reactivex.Flowable
import javax.inject.Inject

class HomeUsecaseImpl @Inject constructor(private val communityRepository: CommunityRepository) :
    HomeUsecase {
    override fun getProfiles(): Flowable<PagingData<Profile>> {
        return communityRepository.getProfile()
    }
}