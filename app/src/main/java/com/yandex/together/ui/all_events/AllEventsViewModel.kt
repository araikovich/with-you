package com.yandex.together.ui.all_events

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import androidx.paging.liveData
import com.yandex.together.R
import com.yandex.together.data.api.EventsApi
import com.yandex.together.data.dataSource.EventDataSource
import com.yandex.together.ui.vo.EventCardVO

class AllEventsViewModel(private val apiService: EventsApi) : ViewModel() {

    val listData = Pager(PagingConfig(pageSize = 20)) {
        EventDataSource(apiService)
    }.liveData.cachedIn(viewModelScope)

    private val _eventsLiveData = MutableLiveData<List<EventCardVO>>()
    val eventsLiveData: LiveData<List<EventCardVO>> = _eventsLiveData
}