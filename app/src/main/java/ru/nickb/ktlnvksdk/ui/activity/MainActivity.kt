package ru.nickb.ktlnvksdk.ui.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.arellomobile.mvp.presenter.InjectPresenter
import com.vk.sdk.VKAccessToken
import com.vk.sdk.VKCallback
import com.vk.sdk.VKSdk
import com.vk.sdk.api.VKError
import ru.nickb.ktlnvksdk.CurrentUser
import ru.nickb.ktlnvksdk.MyApplication
import ru.nickb.ktlnvksdk.R
import ru.nickb.ktlnvksdk.consts.ApiConstants
import ru.nickb.ktlnvksdk.mvp.presenter.MainPresenter
import ru.nickb.ktlnvksdk.mvp.view.MainView
import ru.nickb.ktlnvksdk.ui.fragment.NewsFeedFragment

class MainActivity: BaseActivity(), MainView {

    @InjectPresenter
    lateinit var mPresenter: MainPresenter



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MyApplication.sApplicationComponent.inject(this)

        mPresenter.checkAuth()
    }

    override fun startSignIn() {
        VKSdk.login(this, ApiConstants.DEFAULT_LOGIN_SCOPE)
    }

    override fun signedIn() {
        Toast.makeText(this, "Current user id: " + CurrentUser.getId()!!, Toast.LENGTH_LONG).show()
        setContent(NewsFeedFragment())
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (!// Пользователь успешно авторизовался
            // Произошла ошибка авторизации (например, пользователь запретил авторизацию)
            VKSdk.onActivityResult(
                requestCode,
                resultCode,
                data,
                object : VKCallback<VKAccessToken> {
                    override fun onResult(res: VKAccessToken) {
                        mPresenter.checkAuth()
                    }

                    override fun onError(error: VKError) {}
                })
        ) {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }

    override fun getMainContentLayout(): Int {
        return R.layout.activity_main
    }



}