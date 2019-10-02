package ru.nickb.ktlnvksdk.mvp.presenter

import android.annotation.SuppressLint
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.realm.Realm
import io.realm.RealmObject
import ru.nickb.ktlnvksdk.CurrentUser
import ru.nickb.ktlnvksdk.MyApplication
import ru.nickb.ktlnvksdk.common.manager.NetworkManager
import ru.nickb.ktlnvksdk.model.Profile
import ru.nickb.ktlnvksdk.mvp.view.MainView
import ru.nickb.ktlnvksdk.rest.api.UsersApi
import ru.nickb.ktlnvksdk.rest.model.request.UsersGetRequestModel
import javax.inject.Inject








@InjectViewState
class MainPresenter: MvpPresenter<MainView>() {

    @Inject
    lateinit var mUserApi: UsersApi

    @Inject
    lateinit var mNetworkManager: NetworkManager

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
            .flatMap<Profile> { aBoolean ->
                if (!CurrentUser.isAuthorized()) {
                    viewState.startSignIn()
                }

                if (aBoolean)
                    getProfileFromNetwork()
                else
                    getProfileFromDb()
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { profile -> viewState.showCurrentUser(profile) },
                { error -> error.printStackTrace() })
    }

    fun getProfileFromNetwork(): Observable<Profile> {
        return mUserApi[UsersGetRequestModel(CurrentUser.getId()).toMap()]
            .flatMap<Profile> { listFull -> Observable.fromIterable(listFull.response!!) }
            .doOnNext(this::saveToDb)
    }

    private fun getProfileFromDb(): Observable<Profile> {
        return Observable.fromCallable(getListFromRealmCallable())
    }

    fun saveToDb(item: RealmObject) {
        val realm = Realm.getDefaultInstance()
        realm.executeTransaction { realm1 -> realm1.copyToRealmOrUpdate(item) }
    }

    fun getListFromRealmCallable(): () -> Profile? {
        return {
            val realm = Realm.getDefaultInstance()
            val realmResults = realm.where(Profile::class.java)
                .equalTo("id", Integer.parseInt(CurrentUser.getId()!!))
                .findFirst()
            realm.copyFromRealm(realmResults)
        }
    }
}