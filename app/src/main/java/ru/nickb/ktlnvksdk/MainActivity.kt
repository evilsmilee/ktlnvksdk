package ru.nickb.ktlnvksdk

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.vk.sdk.api.VKError
import com.vk.sdk.VKAccessToken
import com.vk.sdk.VKCallback
import com.vk.sdk.VKSdk
import androidx.core.app.ComponentActivity
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.widget.Toast
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import ru.nickb.ktlnvksdk.const.ApiConstants
import ru.nickb.ktlnvksdk.mvp.presenter.MainPresenter
import ru.nickb.ktlnvksdk.mvp.view.MainView


class MainActivity : MvpAppCompatActivity(), MainView {

    @InjectPresenter
    lateinit var mPresenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mPresenter.checkAuth()

    }

    override fun startSignIn() {
        VKSdk.login(this@MainActivity, ApiConstants.DEFAULT_LOGIN_SCOPE)
    }

    override fun signedIn() {
        Toast.makeText(this, "Current User id + ${CurrentUser.id}", Toast.LENGTH_LONG).show()
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

}
