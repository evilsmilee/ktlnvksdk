package ru.nickb.ktlnvksdk.model.attachment

import com.vk.sdk.api.model.Identifiable

interface Attachment: Identifiable {
    fun getType(): String
}