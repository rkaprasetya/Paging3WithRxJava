package com.example.tandemtest.data.paging

import androidx.paging.rxjava2.RxPagingSource
import com.example.tandemtest.data.api.ApiService
import com.example.tandemtest.data.model.Profile
import com.example.tandemtest.domain.mapper.CommunityMapper
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

class ProfilePagingSource(private val service: ApiService) :
    RxPagingSource<Int, Profile>() {
    override fun loadSingle(params: LoadParams<Int>): Single<LoadResult<Int, Profile>> {
        val position = params.key ?: 1
        return service.getCommunity(position)
            .subscribeOn(Schedulers.io())
            .map { CommunityMapper.convertToProfile(it.response) }
            .map { toLoadResult(it, position) }
            .onErrorReturn { LoadResult.Error(it) }

    }

    private fun toLoadResult(data: List<Profile>, position: Int): LoadResult<Int, Profile> {
        return LoadResult.Page(
            data = data,
            prevKey = if (position == 1) null else position - 1,
            nextKey = if (position == data.size - 1) null else position + 1
        )
    }
}