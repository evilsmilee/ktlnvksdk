package ru.nickb.ktlnvksdk.mvp.view

import com.arellomobile.mvp.MvpView

interface MainView: MvpView {
    fun startSignIn()
    fun signedIn()

}