package ru.nickb.ktlnvksdk.mvp.view

import com.arellomobile.mvp.MvpView
import ru.nickb.ktlnvksdk.model.Profile
import ru.nickb.ktlnvksdk.ui.fragment.BaseFragment

interface MainView: MvpView {
    fun startSignIn()
    fun signedIn()
    fun showCurrentUser(profile: Profile)
    fun showFragmentFromDrawer(baseFragment: BaseFragment)
}