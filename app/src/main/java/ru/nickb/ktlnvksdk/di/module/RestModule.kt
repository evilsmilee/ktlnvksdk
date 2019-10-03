package ru.nickb.ktlnvksdk.di.module

import dagger.Module
import dagger.Provides
import ru.nickb.ktlnvksdk.rest.RestClient
import ru.nickb.ktlnvksdk.rest.api.GroupsApi
import ru.nickb.ktlnvksdk.rest.api.UsersApi
import ru.nickb.ktlnvksdk.rest.api.WallApi

import javax.inject.Singleton

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

    @Provides
    @Singleton
    fun provideUsersApi(): UsersApi {
        return mRestClient.createService(UsersApi::class.java)
    }

    @Provides
    @Singleton
    fun provideGroupsApi(): GroupsApi {
        return mRestClient.createService(GroupsApi::class.java)
    }
}