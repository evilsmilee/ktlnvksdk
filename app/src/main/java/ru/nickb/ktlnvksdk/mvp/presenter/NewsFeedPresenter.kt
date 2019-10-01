package ru.nickb.ktlnvksdk.mvp.presenter

import com.arellomobile.mvp.InjectViewState
import io.reactivex.Observable
import ru.nickb.ktlnvksdk.MyApplication
import ru.nickb.ktlnvksdk.common.utils.VkListHelper
import ru.nickb.ktlnvksdk.model.view.BaseViewModel
import ru.nickb.ktlnvksdk.model.view.NewsItemBodyViewModel
import ru.nickb.ktlnvksdk.model.view.NewsItemFooterViewModel
import ru.nickb.ktlnvksdk.model.view.NewsItemHeaderViewModel
import ru.nickb.ktlnvksdk.mvp.view.BaseFeedView
import ru.nickb.ktlnvksdk.rest.api.WallApi
import ru.nickb.ktlnvksdk.rest.model.request.WallGetRequestModel
import javax.inject.Inject





@InjectViewState
class NewsFeedPresenter: BaseFeedPresenter<BaseFeedView>() {

    @Inject
    lateinit var mWallApi: WallApi

    init {
        MyApplication.sApplicationComponent.inject(this)

    }

    override fun onCreateLoadDataObservable(count: Int, offset: Int): Observable<BaseViewModel> {
        return mWallApi[WallGetRequestModel(-86529522).toMap()]
            .flatMap { full -> Observable.fromIterable(VkListHelper.getWallList(full.response!!)) }
            .flatMap { wallItem ->
                val baseItems: MutableList<BaseViewModel> = ArrayList()
                baseItems.add(NewsItemHeaderViewModel(wallItem))
                baseItems.add(NewsItemBodyViewModel(wallItem))
                baseItems.add(NewsItemFooterViewModel(wallItem))
                Observable.fromIterable<BaseViewModel>(baseItems)
            }
    }
}