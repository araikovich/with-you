package com.yandex.together.data.dataSource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.yandex.together.R
import com.yandex.together.data.api.EventResponse
import com.yandex.together.data.api.EventsApi
import com.yandex.together.data.api.safeApiCall
import com.yandex.together.ui.orZero
import com.yandex.together.ui.vo.EventCardVO

class EventDataSource(private val apiService: EventsApi) :
    PagingSource<Int, EventCardVO>() {

    override fun getRefreshKey(state: PagingState<Int, EventCardVO>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, EventCardVO> {
        try {
            val currentLoadingPageKey = params.key ?: 0
            val response = safeApiCall { apiService.getEvents(currentLoadingPageKey, null) }

            return if (response.isSuccess()) {
                LoadResult.Page(
                    data = map(response.asSuccess().value.content.orEmpty()),
                    prevKey = null,
                    nextKey = if (response.asSuccess().value.content?.isEmpty()
                            ?: true
                    ) null else currentLoadingPageKey.plus(
                        1
                    )
                )
            } else {
                LoadResult.Error(Error(response.asError().msg))
            }
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }

    private fun map(list: List<EventResponse>): List<EventCardVO> {
        return list.map {
            EventCardVO(
                it.id.orZero(),
                mapCategory(it.category.orEmpty()),
                it.category.orEmpty(),
                it.title.orEmpty(),
                it.description.orEmpty(),
                it.membersCount.orZero(),
                it.capacity.orZero(),
                it.time.orEmpty(),
                it.chatLink.orEmpty(),
            )
        }
    }

    private fun mapCategory(category: String): Int {
        return when (category.hashCode() % 7) {
            1 -> R.color.nature_icon
            2 -> R.color.sport_icon
            3 -> R.color.table_games_icon
            else -> R.color.table_games_icon
        }
    }
}