package ru.nickb.ktlnvksdk.di.module

import dagger.Module
import dagger.Provides
import ru.nickb.ktlnvksdk.common.manager.MyFragmentManager
import javax.inject.Singleton


@Module
class ManagerModule {

    @Provides
    @Singleton
    fun provideMyFragmentManager(): MyFragmentManager {
        return MyFragmentManager()
    }

}