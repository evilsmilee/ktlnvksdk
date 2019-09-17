package ru.nickb.ktlnvksdk.rest.model.response


data class BaseItemResponse<T>(var count: Int,
                               var items: List<T>)