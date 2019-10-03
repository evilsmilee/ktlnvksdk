package ru.nickb.ktlnvksdk.di.component

import dagger.Component
import ru.nickb.ktlnvksdk.common.manager.NetworkManager
import ru.nickb.ktlnvksdk.di.module.ApplicationModule
import ru.nickb.ktlnvksdk.di.module.ManagerModule
import ru.nickb.ktlnvksdk.di.module.RestModule
import ru.nickb.ktlnvksdk.mvp.presenter.MainPresenter
import ru.nickb.ktlnvksdk.mvp.presenter.MembersPresenter
import ru.nickb.ktlnvksdk.mvp.presenter.NewsFeedPresenter
import ru.nickb.ktlnvksdk.ui.activity.BaseActivity
import ru.nickb.ktlnvksdk.ui.activity.MainActivity
import ru.nickb.ktlnvksdk.ui.fragment.NewsFeedFragment
import ru.nickb.ktlnvksdk.ui.holder.NewsItemBodyHolder
import ru.nickb.ktlnvksdk.ui.holder.NewsItemFooterHolder
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class, ManagerModule::class, RestModule::class])
interface ApplicationComponent {

    fun inject(activity: BaseActivity)
    fun inject(activity: MainActivity)

    fun inject(fragment: NewsFeedFragment)

    fun inject(holder: NewsItemBodyHolder)
    fun inject(holder: NewsItemFooterHolder)

    fun inject(presenter: NewsFeedPresenter)
    fun inject(presenter: MainPresenter)
    fun inject(presenter: MembersPresenter)

    fun inject(networkManager: NetworkManager)

    }



