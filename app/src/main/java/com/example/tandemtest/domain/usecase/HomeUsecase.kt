package com.example.tandemtest.domain.usecase

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.example.tandemtest.data.model.Profile
import com.example.tandemtest.data.repositoryimpl.CommunityRepositoryImpl
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HomeUsecase @Inject constructor(private val communityRepository: CommunityRepositoryImpl) {
    fun getProfiles(): LiveData<PagingData<Profile>> {
        Log.e("use","use")
      return  communityRepository.getProfile()
    }
}