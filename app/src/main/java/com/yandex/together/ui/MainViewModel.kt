package com.yandex.together.ui

import androidx.lifecycle.ViewModel
import com.yandex.together.data.prefs.PreferencesStorage

class MainViewModel(private val preferencesStorage: PreferencesStorage) : ViewModel() {

    fun isLoggedIn() = preferencesStorage.authToken.isNotEmpty()
}