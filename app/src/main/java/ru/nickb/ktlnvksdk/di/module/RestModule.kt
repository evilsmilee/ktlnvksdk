package ru.nickb.ktlnvksdk.di.module

import dagger.Module
import dagger.Provides
import ru.nickb.ktlnvksdk.rest.RestClient
import javax.inject.Singleton
import ru.nickb.ktlnvksdk.rest.api.WallApi

@Module
class RestModule {



    private val mRestClient = RestClient()

    @Provides
    @Singleton
    fun provideRestClient(): RestClient {
        return mRestClient
    }

    @Provides
    @Singleton
    fun provideWallApi(): WallApi {
        return mRestClient.createService(WallApi::class.java)
    }
}