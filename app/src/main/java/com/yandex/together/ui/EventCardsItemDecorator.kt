package com.yandex.together.ui

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class EventCardsItemDecorator : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        outRect.set(0, parent.context.dpToPx(15), 0, 0)
    }
}