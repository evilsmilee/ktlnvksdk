package ru.nickb.ktlnvksdk.ui.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import com.arellomobile.mvp.presenter.InjectPresenter
import ru.nickb.ktlnvksdk.MyApplication
import ru.nickb.ktlnvksdk.R
import ru.nickb.ktlnvksdk.mvp.presenter.BaseFeedPresenter
import ru.nickb.ktlnvksdk.mvp.presenter.NewsFeedPresenter
import ru.nickb.ktlnvksdk.mvp.view.BaseFeedView
import ru.nickb.ktlnvksdk.rest.api.WallApi
import javax.inject.Inject


class NewsFeedFragment : BaseFeedFragment() {



    @Inject
    lateinit var mWallApi: WallApi

    @InjectPresenter
    lateinit var mPresenter: NewsFeedPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        MyApplication.sApplicationComponent.inject(this)
    }

    @SuppressLint("CheckResult")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }


    override fun onCreateFeedPresenter(): BaseFeedPresenter<BaseFeedView> {
        return mPresenter
    }

    override fun onCreateToolbarTitle(): Int {
        return R.string.screen_name_news
    }
}// Required empty public constructor