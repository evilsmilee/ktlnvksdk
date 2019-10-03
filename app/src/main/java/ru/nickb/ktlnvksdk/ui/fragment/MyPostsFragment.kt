package ru.nickb.ktlnvksdk.ui.fragment

import android.os.Bundle
import ru.nickb.ktlnvksdk.R

class MyPostsFragment: NewsFeedFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mPresenter.applyFilter()
    }

    override fun onCreateToolbarTitle(): Int {
        return R.string.screen_name_my_posts
    }
}