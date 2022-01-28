package com.yandex.together.data.api

import com.google.gson.annotations.SerializedName
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface EventsApi {

    @GET("/events")
    suspend fun getEvents(
        @Query("page") page: Int?,
        @Query("size") size: Int?
    ): Response<EventsResponse>

    @POST("/events")
    suspend fun newEvent(@Body body: NewEventBody): Response<NewEventResponse>
}

data class EventsResponse(
    @SerializedName("totalPages") val totalPages: Int?,
    @SerializedName("content") val content: List<EventResponse>?
)

data class EventResponse(
    @SerializedName("id") val id: Int?,
    @SerializedName("category") val category: String?,
    @SerializedName("title") val title: String?,
    @SerializedName("description") val description: String?,
    @SerializedName("capacity") val capacity: Int?,
    @SerializedName("membersCount") val membersCount: Int?,
    @SerializedName("time") val time: String?,
    @SerializedName("chatLink") val chatLink: String?,
)

data class NewEventBody(
    @SerializedName("owner") val owner: String,
    @SerializedName("title") val title: String?,
    @SerializedName("category") val category: String?,
    @SerializedName("description") val description: String?,
    @SerializedName("chatLink") val chatLink: String?,
    @SerializedName("capacity") val capacity: Int?,
    @SerializedName("time") val time: String?,
    @SerializedName("place") val place: EventAddress?
)

data class EventAddress(
    @SerializedName("lat") val lat: Int?,
    @SerializedName("lon") val lon: Int?,
    @SerializedName("address") val address: String?
)

class NewEventResponse