package ru.nickb.ktlnvksdk.common.manager

import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.LinearLayoutManager

class MyLinearLayoutManager : LinearLayoutManager {

    val isOnNextPagePosition: Boolean
        get() {
            val visibleItemCount = childCount
            val totalItemCount = itemCount
            val pastVisibleItems = findFirstVisibleItemPosition()

            return visibleItemCount + pastVisibleItems >= totalItemCount / 2
        }

    constructor(context: Context) : super(context)

    constructor(context: Context, orientation: Int, reverseLayout: Boolean) : super(
        context,
        orientation,
        reverseLayout
    )

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int, defStyleRes: Int) : super(
        context,
        attrs,
        defStyleAttr,
        defStyleRes
    )
}