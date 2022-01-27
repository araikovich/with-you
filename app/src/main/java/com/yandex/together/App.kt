package com.yandex.together

import android.app.Application
import com.yandex.together.di.networkModule
import com.yandex.together.di.repositoryModule
import com.yandex.together.di.storageModule
import com.yandex.together.di.viewModels
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(viewModels, networkModule, storageModule, repositoryModule)
        }
    }
}