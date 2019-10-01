package ru.nickb.ktlnvksdk.mvp.view

import com.arellomobile.mvp.MvpView
import ru.nickb.ktlnvksdk.model.view.BaseViewModel

interface BaseFeedView: MvpView {

    fun showRefreshing()
    fun hideRefreshing()
    fun showListProgress()
    fun hideListProgress()
    fun showError(message: String)
    fun setItems(items: MutableList<BaseViewModel>)
    fun addItems(items: MutableList<BaseViewModel>)
}