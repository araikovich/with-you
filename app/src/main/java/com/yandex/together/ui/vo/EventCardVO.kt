package com.yandex.together.ui.vo

import androidx.annotation.DrawableRes

data class EventCardVO(
    @DrawableRes val categoryRes: Int,
    val categoryTitle: String,
    val title: String,
    val description: String,
    val currentPersonCount: Int,
    val totalPersonsCount: Int,
    val date: String,
)