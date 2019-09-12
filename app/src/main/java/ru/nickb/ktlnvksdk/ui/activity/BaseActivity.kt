package ru.nickb.ktlnvksdk.ui.activity

import android.os.Bundle
import android.os.PersistableBundle
import android.widget.FrameLayout
import androidx.annotation.LayoutRes
import com.arellomobile.mvp.MvpAppCompatActivity
import kotlinx.android.synthetic.main.toolbar_layout.*
import ru.nickb.ktlnvksdk.R
import ru.nickb.ktlnvksdk.common.MyFragmentManager
import ru.nickb.ktlnvksdk.ui.fragment.BaseFragment


abstract class BaseActivity: MvpAppCompatActivity() {

    lateinit var myFragmentManager: MyFragmentManager

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(R.layout.activity_base)
        setSupportActionBar(toolbar)
        val parent = findViewById<FrameLayout>(R.id.main_wrapper)
        layoutInflater.inflate(getMainContentLayout(), parent)
    }

    fun fragmentOnScreen(baseFragment: BaseFragment) {
        setToolBarTitle(baseFragment.createToolbarTitle(this))
    }

    private fun setToolBarTitle(title: String) {
        supportActionBar?.title = title
    }

    @LayoutRes
    protected  abstract fun getMainContentLayout(): Int

    fun setContent(fragment: BaseFragment) {
        myFragmentManager.setFragment(this, fragment = fragment, containerId = R.id.main_wrapper)
    }

    fun addContent(fragment: BaseFragment) {
        myFragmentManager.addFragment(this, fragment = fragment, containerId = R.id.main_wrapper)
    }

    fun removeCurrentFragment(): Boolean {
        return myFragmentManager.removeCurrentFragment(this)
    }

    fun removeFragment(fragment: BaseFragment): Boolean {
        return myFragmentManager.removeFragment(this, fragment = fragment)
    }

    override fun onBackPressed() {
        removeCurrentFragment()
    }
}