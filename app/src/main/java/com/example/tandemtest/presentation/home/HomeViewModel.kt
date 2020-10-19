package com.example.tandemtest.presentation.home

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.rxjava2.cachedIn
import com.example.tandemtest.data.model.Profile
import com.example.tandemtest.domain.usecase.HomeUsecaseImpl
import io.reactivex.Flowable

class HomeViewModel @ViewModelInject constructor(
    private val usecase: HomeUsecaseImpl
) : ViewModel() {

    fun getProfiles(): Flowable<PagingData<Profile>> {
        return usecase.getProfiles().cachedIn(viewModelScope)
    }
}