package ru.nickb.ktlnvksdk.mvp.presenter

import android.annotation.SuppressLint
import android.util.Log
import com.arellomobile.mvp.MvpPresenter
import io.reactivex.Observable
import io.reactivex.ObservableSource
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Function
import io.reactivex.schedulers.Schedulers
import io.realm.Realm
import io.realm.RealmObject
import ru.nickb.ktlnvksdk.common.manager.NetworkManager
import ru.nickb.ktlnvksdk.model.view.BaseViewModel
import ru.nickb.ktlnvksdk.mvp.view.BaseFeedView
import javax.inject.Inject


abstract class BaseFeedPresenter<V : BaseFeedView> : MvpPresenter<V>() {

    companion object {
        private const val START_PAGE_SIZE: Int = 15
        private const val NEXT_PAGE_SIZE: Int = 5
    }

    @Inject
    lateinit var mNetworkManager: NetworkManager

    private var mIsInLoading: Boolean = false

    @SuppressLint("CheckResult")
    fun loadData(progressType: ProgressType, offset: Int, count: Int) {
        if (mIsInLoading) {
            return
        }
        mIsInLoading = true


        mNetworkManager.getNetworkObservable()
            .flatMap(object : Function<Boolean, ObservableSource<out BaseViewModel>> {
                @Throws(Exception::class)
                override fun apply(aBoolean: Boolean): ObservableSource<out BaseViewModel> {
                    if ((!aBoolean) && offset > 0) {
                        return Observable.empty()
                    }
                    return if (aBoolean)
                       onCreateLoadDataObservable(count, offset)
                    else
                     onCreateRestoreDataObservable()
                }
                }
              )
            .toList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { disposable -> onLoadStart(progressType) }
            .doFinally { onLoadingFinish(progressType) }
            .subscribe(
                { repositories ->
                   onLoadingSuccess(progressType, repositories
                    )
                },
                { error ->
                    error.printStackTrace()
                   onLoadingFailed(error)
                })

    }

    abstract fun onCreateLoadDataObservable(count: Int, offset: Int): Observable<BaseViewModel>

    fun loadStart() {
        loadData(ProgressType.ListProgress, 0, START_PAGE_SIZE)
    }

    fun loadNext(offset: Int) {
        loadData(ProgressType.Paging, offset, NEXT_PAGE_SIZE)
    }

    fun loadRefresh() {
        loadData(ProgressType.Refreshing, 0, START_PAGE_SIZE)
    }

    private fun onLoadStart(progressType: ProgressType) {
        showProgress(progressType)
    }

    private fun onLoadingFinish(progressType: ProgressType) {
        mIsInLoading = false
        hideProgress(progressType)
    }

    private fun onLoadingFailed(throwable: Throwable) {
        viewState.showError(throwable.message!!)
    }

    private fun onLoadingSuccess(progressType: ProgressType, items: MutableList<BaseViewModel>) {
        if (progressType === ProgressType.Paging) {
            viewState.addItems(items)
        } else {
            viewState.setItems(items)
        }
    }

    enum class ProgressType {
        Refreshing, ListProgress, Paging
    }

    private fun showProgress(progressType: ProgressType) {
        when (progressType) {
            ProgressType.Refreshing -> viewState.showRefreshing()
            ProgressType.ListProgress -> viewState.showListProgress()
            else -> Log.i("error", "error")

        }
    }

    private fun hideProgress(progressType: ProgressType) {
        when (progressType) {
            ProgressType.Refreshing -> viewState.hideRefreshing()
            ProgressType.ListProgress -> viewState.hideListProgress()
            else -> Log.i("error", "error")

        }
    }

    abstract fun onCreateRestoreDataObservable(): Observable<BaseViewModel>

    fun saveToDb(item: RealmObject) {
        val realm: Realm = Realm.getDefaultInstance()
        realm.executeTransaction { realm1 -> realm1.copyToRealmOrUpdate(item) }
    }

}