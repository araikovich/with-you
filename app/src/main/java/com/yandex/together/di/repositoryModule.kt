package com.yandex.together.di

import com.yandex.together.data.LoginRepository
import org.koin.dsl.module

val repositoryModule = module {
    factory { LoginRepository(get(), get()) }
}