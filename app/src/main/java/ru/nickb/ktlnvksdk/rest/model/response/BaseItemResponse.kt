package ru.nickb.ktlnvksdk.rest.model.response

import java.util.*

class BaseItemResponse<T> {
    var count: Int? = null
    var items: List<T> = ArrayList()
}