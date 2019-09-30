package ru.nickb.ktlnvksdk.rest.model.response

import java.util.*

open class BaseItemResponse<T> {
    var count: Int? = null
    var items: List<T> = ArrayList()
}