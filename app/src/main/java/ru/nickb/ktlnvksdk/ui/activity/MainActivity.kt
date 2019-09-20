package ru.nickb.ktlnvksdk.ui.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.vk.sdk.api.VKError
import com.vk.sdk.VKAccessToken
import com.vk.sdk.VKCallback
import com.vk.sdk.VKSdk
import android.widget.Toast
import com.arellomobile.mvp.presenter.InjectPresenter
import ru.nickb.ktlnvksdk.CurrentUser
import ru.nickb.ktlnvksdk.MyApplication
import ru.nickb.ktlnvksdk.R
import ru.nickb.ktlnvksdk.const.ApiConstants
import ru.nickb.ktlnvksdk.mvp.presenter.MainPresenter
import ru.nickb.ktlnvksdk.mvp.view.MainView
import ru.nickb.ktlnvksdk.ui.fragment.NewsFeedFragment


class MainActivity : BaseActivity(), MainView {


    @InjectPresenter
    lateinit var mPresenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MyApplication.sApplicationComponent.inject(this)
        mPresenter.checkAuth()
        Log.i("Okay", "step2")

    }



    override fun startSignIn() {
        VKSdk.login(this@MainActivity, ApiConstants.DEFAULT_LOGIN_SCOPE)
    }

    override fun signedIn() {
        Log.i("Okay", "step3")
        Toast.makeText(this, "Current User id + ${CurrentUser.id}", Toast.LENGTH_LONG).show()
        setContent(fragment = NewsFeedFragment())
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
                    override fun onError(error: VKError) {

                    }
                })
        ) {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }

    override fun getMainContentLayout(): Int {
        return R.layout.activity_main
    }
}
