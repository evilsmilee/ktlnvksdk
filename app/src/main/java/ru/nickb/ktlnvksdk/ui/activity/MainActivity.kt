package ru.nickb.ktlnvksdk.ui.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.arellomobile.mvp.presenter.InjectPresenter
import com.mikepenz.google_material_typeface_library.GoogleMaterial
import com.mikepenz.materialdrawer.AccountHeader
import com.mikepenz.materialdrawer.AccountHeaderBuilder
import com.mikepenz.materialdrawer.Drawer
import com.mikepenz.materialdrawer.DrawerBuilder
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem
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

    lateinit var mDrawer: Drawer

    lateinit var mAccountHeader: AccountHeader

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
        setUpDrawer()
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

    fun setUpDrawer() {
        val item1: PrimaryDrawerItem = PrimaryDrawerItem().withIdentifier(1).withName(R.string.screen_name_news).withIcon(GoogleMaterial.Icon.gmd_home)
        val item2: PrimaryDrawerItem = PrimaryDrawerItem().withIdentifier(2).withName(R.string.screen_name_my_posts).withIcon(GoogleMaterial.Icon.gmd_list)
        val item3: PrimaryDrawerItem = PrimaryDrawerItem().withIdentifier(3).withName(R.string.screen_name_settings).withIcon(GoogleMaterial.Icon.gmd_settings)
        val item4: PrimaryDrawerItem = PrimaryDrawerItem().withIdentifier(4).withName(R.string.screen_name_members).withIcon(GoogleMaterial.Icon.gmd_people)
        val item5: PrimaryDrawerItem = PrimaryDrawerItem().withIdentifier(5).withName(R.string.screen_name_topics).withIcon(GoogleMaterial.Icon.gmd_record_voice_over)
        val item6: PrimaryDrawerItem = PrimaryDrawerItem().withIdentifier(6).withName(R.string.screen_name_info).withIcon(GoogleMaterial.Icon.gmd_info)
        val item7: PrimaryDrawerItem = PrimaryDrawerItem().withIdentifier(7).withName(R.string.screen_name_rules).withIcon(GoogleMaterial.Icon.gmd_assessment)



        mAccountHeader = AccountHeaderBuilder()
            .withActivity(this)
            .build()
        mDrawer = DrawerBuilder()
            .withActivity(this)
            .withToolbar(toolbar)
            .withTranslucentStatusBar(true)
            .withActionBarDrawerToggle(true)
            .withAccountHeader(mAccountHeader)
            .build()
    }

}