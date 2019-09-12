package ru.nickb.ktlnvksdk.ui.fragment

import android.os.Bundle
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toolbar
import androidx.annotation.LayoutRes
import androidx.annotation.StringRes
import com.arellomobile.mvp.MvpAppCompatFragment

abstract class BaseFragment: MvpAppCompatFragment() {

    lateinit var mToolbar: Toolbar

    @LayoutRes
    protected abstract fun getMainContentLayout(): Int

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(getMainContentLayout(), container, false)
    }

    fun createToolbarTitle(context: Context): String {
        return context.getString(onCreateToolbarTitle())
    }

    @StringRes
    abstract fun onCreateToolbarTitle(): Int
}