package ru.nickb.ktlnvksdk.ui.fragment

import android.os.Bundle
import android.view.View
import butterknife.ButterKnife
import com.arellomobile.mvp.presenter.InjectPresenter
import ru.nickb.ktlnvksdk.R
import ru.nickb.ktlnvksdk.mvp.presenter.BaseFeedPresenter
import ru.nickb.ktlnvksdk.mvp.presenter.BoardPresenter
import ru.nickb.ktlnvksdk.mvp.view.BaseFeedView

class BoardFragment: BaseFeedFragment() {

    @InjectPresenter
    lateinit var mPresenter: BoardPresenter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ButterKnife.bind(this, view)
    }

    override fun onCreateFeedPresenter(): BaseFeedPresenter<BaseFeedView> {
        return mPresenter
    }

    override fun onCreateToolbarTitle(): Int {
        return R.string.screen_name_topics
    }
}