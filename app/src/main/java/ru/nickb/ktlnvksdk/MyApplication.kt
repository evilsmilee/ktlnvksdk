package ru.nickb.ktlnvksdk

import android.app.Application
import com.vk.sdk.VKSdk
import ru.nickb.ktlnvksdk.di.component.ApplicationComponent
import ru.nickb.ktlnvksdk.di.component.DaggerApplicationComponent
import ru.nickb.ktlnvksdk.di.module.ApplicationModule


class MyApplication: Application() {

    companion object {

        lateinit var sApplicationComponent: ApplicationComponent
    }


    override fun onCreate() {
        super.onCreate()
        VKSdk.initialize(applicationContext)
        initComponent()
    }

    private fun initComponent() {
        sApplicationComponent = DaggerApplicationComponent.builder()
            .applicationModule(ApplicationModule(this)).build()
    }



}