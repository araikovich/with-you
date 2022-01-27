package com.yandex.together.di

import android.content.Context
import com.yandex.together.data.prefs.PreferencesStorage
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val storageModule = module {
    single {
        PreferencesStorage(
            androidApplication().getSharedPreferences(
                "prefs",
                Context.MODE_PRIVATE
            )
        )
    }
}