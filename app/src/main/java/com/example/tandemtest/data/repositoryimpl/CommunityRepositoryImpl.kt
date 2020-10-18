package com.example.tandemtest.data.repositoryimpl

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.example.tandemtest.data.api.ApiService
import com.example.tandemtest.data.model.Profile
import com.example.tandemtest.data.paging.ProfilePagingSource
import com.example.tandemtest.domain.repository.CommunityRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CommunityRepositoryImpl @Inject constructor(private val api: ApiService) : CommunityRepository {
    override fun getProfile(): LiveData<PagingData<Profile>> = Pager(config = PagingConfig(
        pageSize = 20,
        maxSize = 500,
        enablePlaceholders = false
    ),
        pagingSourceFactory = { ProfilePagingSource(api) }).liveData
}