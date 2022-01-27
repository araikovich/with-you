package com.yandex.together.ui

import android.content.Context
import com.yandex.together.R

fun Context.dpToPx(dp: Int) = (dp * resources.displayMetrics.density).toInt()

fun Context.toolBarHeight(): Int {
    val attrs = intArrayOf(R.attr.actionBarSize)
    val typedArray = obtainStyledAttributes(attrs)
    val toolBarHeight = typedArray.getDimensionPixelSize(0, -1)
    typedArray.recycle()
    return toolBarHeight
}

fun Int?.orZero() = this ?: 0