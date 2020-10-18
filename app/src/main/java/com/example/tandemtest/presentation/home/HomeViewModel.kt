package com.example.tandemtest.presentation.home

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.tandemtest.data.model.Profile
import com.example.tandemtest.domain.usecase.HomeUsecase

class HomeViewModel @ViewModelInject constructor(
    private val usecase: HomeUsecase
) : ViewModel() {

    fun getProfiles(): LiveData<PagingData<Profile>> {
        return usecase.getProfiles().cachedIn(viewModelScope)
    }
}