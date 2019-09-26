package ru.nickb.ktlnvksdk

import com.vk.sdk.VKAccessToken
import com.vk.sdk.VKSdk

object CurrentUser {
    fun getAccessToken(): String? {
        return if (VKAccessToken.currentToken() == null) {
            null
        } else VKAccessToken.currentToken().accessToken

    }

    fun getId(): String? {
        return if (VKAccessToken.currentToken() != null) {
            VKAccessToken.currentToken().userId
        } else null

    }

    fun isAuthorized(): Boolean {
        return (VKSdk.isLoggedIn()
                && VKAccessToken.currentToken() != null
                && !VKAccessToken.currentToken().isExpired)
    }

}