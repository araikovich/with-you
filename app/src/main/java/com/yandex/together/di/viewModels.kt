package com.yandex.together.di

import com.yandex.together.ui.all_events.AllEventsViewModel
import com.yandex.together.ui.my_events.MyEventsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModels = module {

    viewModel { AllEventsViewModel() }
    viewModel { MyEventsViewModel() }

}