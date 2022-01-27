package com.yandex.together.data

import com.yandex.together.data.api.*
import com.yandex.together.data.prefs.PreferencesStorage

class LoginRepository(
    private val loginApi: LoginApi,
    private val preferencesStorage: PreferencesStorage
) {

    suspend fun login(login: String, password: String): ResultWrapper<LoginResponse> {
        val result = safeApiCall { loginApi.login(LoginBody(login, password)) }
        if (result.isSuccess()) {
            preferencesStorage.authToken = result.asSuccess().value.token.orEmpty()
        }
        return result
    }
}