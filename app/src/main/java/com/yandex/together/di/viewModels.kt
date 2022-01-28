package com.yandex.together.di

import com.yandex.together.ui.MainViewModel
import com.yandex.together.ui.all_events.AllEventsViewModel
import com.yandex.together.ui.login.LoginViewModel
import com.yandex.together.ui.my_events.MyEventsViewModel
import com.yandex.together.ui.new_event.NewEventViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModels = module {

    viewModel { AllEventsViewModel(get()) }
    viewModel { MyEventsViewModel() }
    viewModel { LoginViewModel(get()) }
    viewModel { MainViewModel(get()) }
    viewModel { NewEventViewModel(get()) }
}