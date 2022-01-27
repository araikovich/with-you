package com.yandex.together.di

import com.yandex.together.data.api.LogInInterceptor
import com.yandex.together.data.api.LoginApi
import com.yandex.together.data.prefs.PreferencesStorage
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {
    single { provideRetrofit(get()) }
    factory { provideLoginInterceptor(get()) }
    factory { provideOkHttp(get()) }
    factory { provideLoginApi(get()) }
}

fun provideOkHttp(logInInterceptor: LogInInterceptor): OkHttpClient {
    return OkHttpClient.Builder()
        .addInterceptor(logInInterceptor)
        .build()
}

fun provideLoginInterceptor(prefsStorage: PreferencesStorage): LogInInterceptor {
    return LogInInterceptor(prefsStorage)
}

fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder().baseUrl("http://51.250.14.207:8080").client(okHttpClient)
        .addConverterFactory(
            GsonConverterFactory.create()
        ).build()
}

fun provideLoginApi(retrofit: Retrofit): LoginApi {
    return retrofit.create(LoginApi::class.java)
}