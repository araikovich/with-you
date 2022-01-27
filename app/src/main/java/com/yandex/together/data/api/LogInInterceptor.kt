package com.yandex.together.data.api

import com.yandex.together.data.prefs.PreferencesStorage
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class LogInInterceptor(private val prefsStorage: PreferencesStorage): Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request: Request = chain.request()
        val newRequest = request.newBuilder()
            .addHeader("Authorization", prefsStorage.authToken)
            .build()
        return chain.proceed(newRequest)
    }
}