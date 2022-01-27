package com.yandex.together.data.api

import com.google.gson.annotations.SerializedName
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginApi {

    @POST("/login")
    suspend fun login(@Body loginBody: LoginBody): Response<LoginResponse>
}

data class LoginBody(
    @SerializedName("login") val login: String,
    @SerializedName("password") val password: String,
)

data class LoginResponse(
    @SerializedName("token") val token: String?
)