package ru.nickb.ktlnvksdk.ui.activity

import android.os.Bundle
import android.widget.FrameLayout
import android.widget.ProgressBar
import androidx.annotation.LayoutRes
import androidx.appcompat.widget.Toolbar
import butterknife.BindView
import butterknife.ButterKnife
import com.arellomobile.mvp.MvpAppCompatActivity
import ru.nickb.ktlnvksdk.MyApplication
import ru.nickb.ktlnvksdk.R
import ru.nickb.ktlnvksdk.common.manager.MyFragmentManager
import ru.nickb.ktlnvksdk.ui.fragment.BaseFragment
import javax.inject.Inject

abstract class BaseActivity: MvpAppCompatActivity() {

    @Inject
    lateinit var myFragmentManager: MyFragmentManager

    @BindView(R.id.toolbar)
    lateinit var toolbar: Toolbar

    @BindView(R.id.progress)
    lateinit var mProgressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)
        ButterKnife.bind(this)
        MyApplication.sApplicationComponent.inject(this)

        setSupportActionBar(toolbar)


        val parent = findViewById<FrameLayout>(R.id.main_wrapper)
        layoutInflater.inflate(getMainContentLayout(), parent)

    }


    @LayoutRes
    protected abstract fun getMainContentLayout(): Int


    fun fragmentOnScreen(baseFragment: BaseFragment) {
        setToolbarTitle(baseFragment.createToolbarTitle(this))
    }


    private fun setToolbarTitle(title: String) {
        if (supportActionBar != null) {
            supportActionBar!!.title = title
        }
    }


    fun setContent(fragment: BaseFragment) {

        myFragmentManager.setFragment(this, fragment, R.id.main_wrapper)
    }

    fun addContent(fragment: BaseFragment) {
        myFragmentManager.addFragment(this, fragment, R.id.main_wrapper)
    }

    fun removeCurrentFragment(): Boolean {
        return myFragmentManager.removeCurrentFragment(this)
    }

    fun removeFragment(fragment: BaseFragment): Boolean {
        return myFragmentManager.removeFragment(this, fragment)
    }


    override fun onBackPressed() {
        removeCurrentFragment()
    }

    fun getProgressBar(): ProgressBar {
        return mProgressBar
    }

}