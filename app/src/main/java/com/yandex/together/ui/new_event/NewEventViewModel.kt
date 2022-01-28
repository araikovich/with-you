package com.yandex.together.ui.new_event

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yandex.together.data.EventRepository
import com.yandex.together.data.api.NewEventBody
import com.yandex.together.data.api.NewEventResponse
import com.yandex.together.data.api.ResultWrapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NewEventViewModel(private val repository: EventRepository) : ViewModel() {

    private val _eventsLiveData = MutableLiveData<ResultWrapper<NewEventResponse>>()
    val eventsLiveData: LiveData<ResultWrapper<NewEventResponse>> = _eventsLiveData

    fun newEvent(event: NewEventBody) {
        viewModelScope.launch(Dispatchers.IO) {
            _eventsLiveData.postValue(repository.newEvent(event))
        }
    }
}