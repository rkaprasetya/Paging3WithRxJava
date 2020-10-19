package com.example.tandemtest.domain.repository

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingData
import com.example.tandemtest.data.model.Profile
import io.reactivex.Flowable

interface CommunityRepository {
    fun getProfile(): Flowable<PagingData<Profile>>
}