package com.example.tandemtest.data.repositoryimpl

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.rxjava2.flowable
import com.example.tandemtest.data.api.ApiService
import com.example.tandemtest.data.model.Profile
import com.example.tandemtest.data.paging.ProfilePagingSource
import com.example.tandemtest.domain.repository.CommunityRepository
import io.reactivex.Flowable
import javax.inject.Inject

class CommunityRepositoryImpl @Inject constructor(private val api: ApiService) :
    CommunityRepository {
    override fun getProfile(): Flowable<PagingData<Profile>> = Pager(config = PagingConfig(
        pageSize = 20,
        maxSize = 500,
        enablePlaceholders = false
    ),
        pagingSourceFactory = { ProfilePagingSource(api) }).flowable
}