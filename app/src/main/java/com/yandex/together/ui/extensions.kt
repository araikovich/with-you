package com.yandex.together.ui

import android.content.Context

fun Context.dpToPx(dp: Int) = (dp * resources.displayMetrics.density).toInt()