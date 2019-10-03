package ru.nickb.ktlnvksdk.ui.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.arellomobile.mvp.presenter.InjectPresenter
import com.mikepenz.iconics.typeface.library.googlematerial.GoogleMaterial
import com.mikepenz.materialdrawer.AccountHeader
import com.mikepenz.materialdrawer.AccountHeaderBuilder
import com.mikepenz.materialdrawer.Drawer
import com.mikepenz.materialdrawer.DrawerBuilder
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem
import com.mikepenz.materialdrawer.model.ProfileDrawerItem
import com.mikepenz.materialdrawer.model.ProfileSettingDrawerItem
import com.mikepenz.materialdrawer.model.SectionDrawerItem
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem
import com.mikepenz.materialdrawer.model.interfaces.IProfile
import com.vk.sdk.VKAccessToken
import com.vk.sdk.VKCallback
import com.vk.sdk.VKSdk
import com.vk.sdk.api.VKError
import ru.nickb.ktlnvksdk.CurrentUser
import ru.nickb.ktlnvksdk.MyApplication
import ru.nickb.ktlnvksdk.R
import ru.nickb.ktlnvksdk.consts.ApiConstants
import ru.nickb.ktlnvksdk.model.Profile
import ru.nickb.ktlnvksdk.mvp.presenter.MainPresenter
import ru.nickb.ktlnvksdk.mvp.view.MainView
import ru.nickb.ktlnvksdk.ui.fragment.BaseFragment
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

    override fun showFragmentFromDrawer(baseFragment: BaseFragment) {
        setContent(baseFragment)
    }

    override fun showCurrentUser(profile: Profile) {
        val profileDrawerItems: MutableList<IProfile<*>>
        profileDrawerItems = ArrayList()
        profileDrawerItems.add(ProfileDrawerItem().withName(profile.getFullName()).withEmail(VKAccessToken.currentToken().email)
            .withIcon(profile.getDisplayProfilePhoto()!!))
        profileDrawerItems.add(ProfileSettingDrawerItem().withName("Logout")
            .withOnDrawerItemClickListener(object : Drawer.OnDrawerItemClickListener{
                override fun onItemClick(
                    view: View?,
                    position: Int,
                    drawerItem: IDrawerItem<*>
                ): Boolean {
                    mAccountHeader.removeProfile(0)
                    mAccountHeader.removeProfile(0)
                    VKSdk.logout()
                    return false
                }

            }))

        mAccountHeader.profiles = profileDrawerItems

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
        return ru.nickb.ktlnvksdk.R.layout.activity_main
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
            .addDrawerItems(item1, item2, item3, SectionDrawerItem().withName("Группа"), item4, item5, item6, item7)
            .withOnDrawerItemClickListener(object : Drawer.OnDrawerItemClickListener {
                override fun onItemClick(
                    view: View?,
                    position: Int,
                    drawerItem: IDrawerItem<*>
                ): Boolean {
                    mPresenter.drawerItemClick(drawerItem.identifier.toInt())
                    return false
                }
            }
            )
            .build()
    }

}