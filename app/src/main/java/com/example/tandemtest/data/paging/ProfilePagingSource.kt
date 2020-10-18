package com.example.tandemtest.data.paging

import android.util.Log
import androidx.paging.PagingSource
import com.example.tandemtest.data.api.ApiService
import com.example.tandemtest.domain.mapper.CommunityMapper
import com.example.tandemtest.data.model.Profile
import retrofit2.HttpException
import java.io.IOException

class ProfilePagingSource(private val communityApi: ApiService) :
    PagingSource<Int, Profile>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Profile> {
        val position = params.key ?: STARTING_PAGE_INDEX
        return try {
            val response = communityApi.getCommunity(position)
            val profile = response.response?.map { CommunityMapper.convertToProfile(it) }
            LoadResult.Page(
                data = profile!!,
                prevKey = if (position == STARTING_PAGE_INDEX) null else position - 1,
                nextKey = if (profile.isEmpty()) null else position + 1
            )
        } catch (exception: IOException) {
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }
    }

    companion object {
        private const val STARTING_PAGE_INDEX = 1
    }
}