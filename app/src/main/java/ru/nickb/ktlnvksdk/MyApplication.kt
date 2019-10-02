package ru.nickb.ktlnvksdk

import android.app.Application
import com.vk.sdk.VKSdk
import io.realm.Realm
import io.realm.RealmConfiguration
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

        Realm.init(this)
        val realmConfiguration: RealmConfiguration = RealmConfiguration.Builder()
            .deleteRealmIfMigrationNeeded()
            .build()
        Realm.setDefaultConfiguration(realmConfiguration)
    }

    private fun initComponent() {
        sApplicationComponent = DaggerApplicationComponent.builder()
            .applicationModule(ApplicationModule(this)).build()
    }


    fun getApplicationComponent(): ApplicationComponent? {
        return sApplicationComponent
    }
}