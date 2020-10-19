package com.example.tandemtest.domain.usecase

import androidx.paging.PagingData
import com.example.tandemtest.data.model.Profile
import io.reactivex.Flowable

interface HomeUsecase {
    fun getProfiles(): Flowable<PagingData<Profile>>
}