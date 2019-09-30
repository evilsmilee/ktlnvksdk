package ru.nickb.ktlnvksdk.model

import com.vk.sdk.api.model.Identifiable

interface Attachment: Identifiable {
    fun getType(): String
}