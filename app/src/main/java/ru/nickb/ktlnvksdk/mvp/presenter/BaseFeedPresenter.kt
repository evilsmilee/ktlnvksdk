package ru.nickb.ktlnvksdk.mvp.presenter

import android.annotation.SuppressLint
import android.util.Log
import com.arellomobile.mvp.MvpPresenter
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ru.nickb.ktlnvksdk.model.view.BaseViewModel
import ru.nickb.ktlnvksdk.mvp.view.BaseFeedView


abstract class BaseFeedPresenter<V : BaseFeedView> : MvpPresenter<V>() {

    companion object {
        private const val START_PAGE_SIZE: Int = 15
        private const val NEXT_PAGE_SIZE: Int = 5
    }


    private var mIsInLoading: Boolean = false

    @SuppressLint("CheckResult")
    fun loadData(progressType: ProgressType, offset: Int, count: Int) {
        if (mIsInLoading) {
            return
        }
        mIsInLoading = true

        onCreateLoadDataObservable(count, offset)
            .toList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { onLoadingStart(progressType) }
            .doFinally { onLoadingFinish(progressType) }
            .subscribe({ repositories -> onLoadingSuccess(progressType, repositories) }, { error ->
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

    private fun onLoadingStart(progressType: ProgressType) {
        showProgress(progressType)
    }

    private fun onLoadingFinish(progressType: ProgressType) {
        mIsInLoading = false
        hideProgress(progressType)
    }

    private fun onLoadingFailed(throwable: Throwable) {
        viewState.showError(throwable.localizedMessage)
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

}