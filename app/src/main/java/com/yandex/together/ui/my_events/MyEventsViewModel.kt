package com.yandex.together.ui.my_events

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.yandex.together.R
import com.yandex.together.ui.vo.EventCardVO

class MyEventsViewModel() : ViewModel() {

    private val _eventsLiveData = MutableLiveData<List<EventCardVO>>()
    val eventsLiveData: LiveData<List<EventCardVO>> = _eventsLiveData

}