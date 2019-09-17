package ru.nickb.ktlnvksdk

import com.vk.sdk.VKAccessToken
import com.vk.sdk.VKSdk

object CurrentUser {

    val accessToken: String?
        get() = if (VKAccessToken.currentToken() == null) {
            null
        } else VKAccessToken.currentToken().accessToken


    val id: String?
        get() {
            return if (VKAccessToken.currentToken() != null) {
                VKAccessToken.currentToken().userId
            } else null

        }

    val isAuthorized: Boolean
        get() = (VKSdk.isLoggedIn()
                && VKAccessToken.currentToken() != null
                && !VKAccessToken.currentToken().isExpired)

}