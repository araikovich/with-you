package com.yandex.together.data

import android.util.Log
import com.yandex.together.data.api.*

class EventRepository(private val api: EventsApi) {

    suspend fun newEvent(event: NewEventBody): ResultWrapper<NewEventResponse> {
        val result = safeApiCall { api.newEvent(event) }
        if(result.isSuccess()){
            Log.d("wtf", "dfsf")
        }
        return result
    }
}