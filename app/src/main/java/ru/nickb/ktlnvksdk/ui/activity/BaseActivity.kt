package ru.nickb.ktlnvksdk.ui.activity

import android.os.Bundle
import android.util.Log
import android.widget.FrameLayout
import androidx.annotation.LayoutRes
import androidx.appcompat.widget.Toolbar
import com.arellomobile.mvp.MvpAppCompatActivity
import ru.nickb.ktlnvksdk.MyApplication
import ru.nickb.ktlnvksdk.R
import ru.nickb.ktlnvksdk.common.manager.MyFragmentManager
import ru.nickb.ktlnvksdk.ui.fragment.BaseFragment
import javax.inject.Inject

abstract class BaseActivity: MvpAppCompatActivity() {

    @Inject
    lateinit var myFragmentManager: MyFragmentManager

    lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)
        Log.i("Okay", "step1")
        MyApplication.sApplicationComponent.inject(this)

        toolbar = findViewById(R.id.toolbar)
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

}