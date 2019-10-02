package ru.nickb.ktlnvksdk.di.module

import dagger.Module
import dagger.Provides
import ru.nickb.ktlnvksdk.common.manager.MyFragmentManager
import ru.nickb.ktlnvksdk.common.manager.NetworkManager
import javax.inject.Singleton


@Module
class ManagerModule {

    @Provides
    @Singleton
    fun provideMyFragmentManager(): MyFragmentManager {
        return MyFragmentManager()
    }

    @Provides
    @Singleton
    fun provideNetworkManager(): NetworkManager {
        return NetworkManager()
    }

}