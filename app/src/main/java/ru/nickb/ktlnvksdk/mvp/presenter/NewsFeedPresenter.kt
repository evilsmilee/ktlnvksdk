package ru.nickb.ktlnvksdk.mvp.presenter

import com.arellomobile.mvp.InjectViewState
import io.reactivex.Observable
import io.realm.Realm
import io.realm.Sort
import ru.nickb.ktlnvksdk.MyApplication
import ru.nickb.ktlnvksdk.common.utils.VkListHelper
import ru.nickb.ktlnvksdk.model.WallItem
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
        return mWallApi[WallGetRequestModel(-86529522, count, offset).toMap()]
            .flatMap { full -> Observable.fromIterable(VkListHelper.getWallList(full.response!!)) }
            .doOnNext{wallItem -> saveToDb(wallItem)}
            .flatMap { wallItem ->
                val baseItems: MutableList<BaseViewModel> = ArrayList()
                baseItems.add(NewsItemHeaderViewModel(wallItem))
                baseItems.add(NewsItemBodyViewModel(wallItem))
                baseItems.add(NewsItemFooterViewModel(wallItem))
                Observable.fromIterable<BaseViewModel>(baseItems)
            }
    }

    override fun onCreateRestoreDataObservable(): Observable<BaseViewModel> {
        return Observable.fromIterable(getListFromCallable())
            .flatMap { wallItem -> Observable.fromIterable(parsePojoModel(wallItem)) }
    }


    private fun parsePojoModel(wallItem: WallItem): MutableList<BaseViewModel> {
        val baseItems: MutableList<BaseViewModel> = ArrayList()
        baseItems.add(NewsItemHeaderViewModel(wallItem))
        baseItems.add(NewsItemBodyViewModel(wallItem))
        baseItems.add(NewsItemFooterViewModel(wallItem))
        return baseItems
    }

    fun getListFromCallable(): MutableList<WallItem>? {

            val sortFields = arrayOf("date")
            val sortOrder = arrayOf(Sort.DESCENDING)
            val realm = Realm.getDefaultInstance()
            val realmResults = realm.where(WallItem::class.java)
                .sort(sortFields, sortOrder).findAll()
          return  realm.copyFromRealm(realmResults)

    }
}


