package ru.nickb.ktlnvksdk.rest.model.response


class BaseItemResponse<T> {
    var count: Int = 0
    var items: List<T> = ArrayList()
}