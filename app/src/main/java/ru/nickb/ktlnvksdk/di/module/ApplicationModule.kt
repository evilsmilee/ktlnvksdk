package ru.nickb.ktlnvksdk.di.module

import android.app.Application
import dagger.Module
import android.content.Context
import android.graphics.Typeface
import android.view.LayoutInflater
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule {

    private var mApplication: Application

    constructor(application: Application) {
        mApplication = application
    }


    @Singleton
    @Provides
    fun provideContext(): Context {
        return mApplication
    }

    @Singleton
    @Provides
    fun provideLayoutInflater(): LayoutInflater {
        return mApplication.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    }

    @Provides
    @Singleton
    fun provideGoogleFontTypeFace(context: Context): Typeface {
        return Typeface.createFromAsset(context.assets, "MaterialIconsRegular.ttf")
    }


}