package ru.nickb.ktlnvksdk.mvp.view

import com.arellomobile.mvp.MvpView
import ru.nickb.ktlnvksdk.model.Profile

interface MainView: MvpView {
    fun startSignIn()
    fun signedIn()
    fun showCurrentUser(profile: Profile)
}