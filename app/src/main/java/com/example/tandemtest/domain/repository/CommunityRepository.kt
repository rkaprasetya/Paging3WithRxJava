package com.example.tandemtest.domain.repository

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingData
import com.example.tandemtest.data.model.Profile

interface CommunityRepository {
    fun getProfile(): LiveData<PagingData<Profile>>
}