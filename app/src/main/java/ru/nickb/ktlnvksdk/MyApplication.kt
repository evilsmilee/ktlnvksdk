package ru.nickb.ktlnvksdk

import android.app.Application
import com.vk.sdk.VKSdk

class MyApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        VKSdk.initialize(applicationContext)
    }
}