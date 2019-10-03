package ru.nickb.ktlnvksdk.consts

import com.vk.sdk.VKScope

open class ApiConstants {
    companion object {
       const val DEFAULT_LOGIN_SCOPE: String = VKScope.EMAIL
       const val  DEFAULT_VERSION: Double = 5.67
       const val DEFAULT_COUNT: Int = 10
       const val DEFAULT_USERS_FIELDS = "photo_100"
       const val DEFAULT_MEMBER_FIELDS = "name,photo_100"
       const val MY_GROUP_ID = -86529522
    }


}