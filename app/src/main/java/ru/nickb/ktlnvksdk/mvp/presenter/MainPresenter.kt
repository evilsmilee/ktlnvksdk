package ru.nickb.ktlnvksdk.mvp.presenter

import android.annotation.SuppressLint
import android.util.Log
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import io.reactivex.Observable
import io.reactivex.ObservableSource
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Function
import io.reactivex.schedulers.Schedulers
import io.realm.Realm
import io.realm.RealmObject
import ru.nickb.ktlnvksdk.CurrentUser
import ru.nickb.ktlnvksdk.MyApplication
import ru.nickb.ktlnvksdk.common.manager.MyFragmentManager
import ru.nickb.ktlnvksdk.common.manager.NetworkManager
import ru.nickb.ktlnvksdk.model.Profile
import ru.nickb.ktlnvksdk.mvp.view.MainView
import ru.nickb.ktlnvksdk.rest.api.UsersApi
import ru.nickb.ktlnvksdk.rest.model.request.UsersGetRequestModel
import ru.nickb.ktlnvksdk.rest.model.response.Full
import ru.nickb.ktlnvksdk.ui.fragment.*
import java.util.concurrent.Callable
import javax.inject.Inject



@InjectViewState
class MainPresenter: MvpPresenter<MainView>() {

    @Inject
    lateinit var mUserApi: UsersApi

    @Inject
    lateinit var mNetworkManager: NetworkManager

    @Inject
    lateinit var myFragmentManager: MyFragmentManager

    init {
        MyApplication.sApplicationComponent.inject(this)
    }

    fun checkAuth() {
        if(!CurrentUser.isAuthorized()) {
            viewState.startSignIn()
        } else {
            getCurrentUser()
            viewState.signedIn()
        }
    }

    @SuppressLint("CheckResult")
    private fun getCurrentUser() {
        mNetworkManager.getNetworkObservable()
            .flatMap { aBoolean ->
                if (!CurrentUser.isAuthorized()) {
                    viewState.startSignIn()
                }

                if (!aBoolean) { getProfileFromDb()}


                else  {
                    Log.i("profileinfo1", "profileinfo1")
                    getProfileFromNetwork()}

            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ profile -> viewState.showCurrentUser(profile)  },
                { error -> error.printStackTrace() })
    }



    fun drawerItemClick(id: Int) {
        var fragment: BaseFragment? = null
        when(id) {
            1 -> fragment = NewsFeedFragment()
            2 -> fragment = MyPostsFragment()
            4 -> fragment = MembersFragment()
            5 -> fragment = BoardFragment()
            6 -> fragment = InfoFragment()
        }

        if(fragment != null && !myFragmentManager.isAlreadyContains(fragment)) {
            viewState.showFragmentFromDrawer(fragment)
        }

    }


    fun getProfileFromNetwork(): Observable<Profile> {
        Log.i("profileinfo1", CurrentUser.getId())
        return mUserApi.get(UsersGetRequestModel(CurrentUser.getId()).toMap())
            .flatMap(object : Function<Full<List<Profile>>, ObservableSource<out Profile>> {
                override fun apply(t: Full<List<Profile>>): ObservableSource<out Profile> {
                    Log.i("profileinfo1", t.response?.get(0)?.getLastName())
                    return Observable.fromIterable(t.response)
                }

            })
            .doOnNext{item -> saveToDb(item)}
    }

/*    fun getProfileFromNetwork(): Observable<Profile> {
        Log.i("profileinfo1", CurrentUser.getId())
        return mUserApi[UsersGetRequestModel(CurrentUser.getId()).toMap()]
            .flatMap(object : Function<Full<List<Profile>>, ObservableSource<Profile>> {
                override fun apply(t: Full<List<Profile>>): ObservableSource< Profile>  {
                    Log.i("prfoile1", t.response!![0].getLastName())
                    return Observable.fromIterable(t.response)
                }

            })
            .doOnNext{item -> saveToDb(item)}

    }*/





    private fun getProfileFromDb(): Observable<Profile> {
        return Observable.fromCallable(getListFromRealmCallable())
    }

    fun saveToDb(item: RealmObject) {
        val realm = Realm.getDefaultInstance()
        realm.executeTransaction { realm1 -> realm1.copyToRealmOrUpdate(item) }
    }

    fun getListFromRealmCallable(): Callable<Profile> {
        return Callable<Profile> {
            val realm = Realm.getDefaultInstance()
            val realmResults = realm.where(Profile::class.java)
                .equalTo("id", Integer.parseInt(CurrentUser.getId()!!))
                .findFirst()
            realm.copyFromRealm(realmResults)
        }
    }
}