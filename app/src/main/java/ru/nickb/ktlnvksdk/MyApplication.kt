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

        initComponent()

        VKSdk.initialize(this)
    }

    private fun initComponent() {
        sApplicationComponent = DaggerApplicationComponent.builder()
            .applicationModule(ApplicationModule(this)).build()
    }

    fun getApplicationComponent(): ApplicationComponent? {
        return sApplicationComponent
    }
}