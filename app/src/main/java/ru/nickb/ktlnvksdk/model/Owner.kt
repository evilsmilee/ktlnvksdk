package ru.nickb.ktlnvksdk.model

import com.vk.sdk.api.model.Identifiable

interface Owner: Identifiable {

   fun getFullName(): String
  fun getPhoto(): String


}