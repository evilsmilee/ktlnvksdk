package ru.nickb.ktlnvksdk.mvp.presenter

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import ru.nickb.ktlnvksdk.CurrentUser
import ru.nickb.ktlnvksdk.mvp.view.MainView


@InjectViewState
class MainPresenter: MvpPresenter<MainView>() {

    fun checkAuth() {
        if(!CurrentUser.isAuthorized) {
            viewState.startSignIn()
        } else {
            viewState.signedIn()
        }
    }

}