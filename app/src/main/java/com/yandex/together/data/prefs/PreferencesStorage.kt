package com.yandex.together.data.prefs

import android.content.SharedPreferences

class PreferencesStorage(
    private val preferencesStorage: SharedPreferences,
) {

    var authToken by PrefsDelegate(preferencesStorage, AUTHORIZATION, "")

    companion object {
        private const val AUTHORIZATION = "AUTHORIZATION"
    }
}