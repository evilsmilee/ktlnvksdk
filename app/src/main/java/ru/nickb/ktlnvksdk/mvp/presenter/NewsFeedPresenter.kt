package ru.nickb.ktlnvksdk.mvp.presenter

import com.arellomobile.mvp.InjectViewState
import io.reactivex.Observable
import io.reactivex.ObservableTransformer
import io.realm.Realm
import io.realm.Sort
import ru.nickb.ktlnvksdk.CurrentUser
import ru.nickb.ktlnvksdk.MyApplication
import ru.nickb.ktlnvksdk.common.utils.VkListHelper
import ru.nickb.ktlnvksdk.consts.ApiConstants
import ru.nickb.ktlnvksdk.model.WallItem
import ru.nickb.ktlnvksdk.model.view.BaseViewModel
import ru.nickb.ktlnvksdk.model.view.NewsItemBodyViewModel
import ru.nickb.ktlnvksdk.model.view.NewsItemFooterViewModel
import ru.nickb.ktlnvksdk.model.view.NewsItemHeaderViewModel
import ru.nickb.ktlnvksdk.mvp.view.BaseFeedView
import ru.nickb.ktlnvksdk.rest.api.WallApi
import ru.nickb.ktlnvksdk.rest.model.request.WallGetRequestModel
import java.util.concurrent.Callable
import javax.inject.Inject






@InjectViewState
class NewsFeedPresenter: BaseFeedPresenter<BaseFeedView>() {


    @Inject
    lateinit var mWallApi: WallApi

    private val enableIdFiltering = false

    init {
        MyApplication.sApplicationComponent.inject(this)

    }

     fun applyFilter(): ObservableTransformer<WallItem, WallItem> {
        return if (enableIdFiltering && CurrentUser.getId() != null) {
            ObservableTransformer { baseItemObservable -> baseItemObservable.filter { t: WallItem -> CurrentUser.getId()!!.equals(t.fromId) } }
        } else {
            ObservableTransformer { baseItemObservable -> baseItemObservable }
        }
    }

    override fun onCreateLoadDataObservable(count: Int, offset: Int): Observable<BaseViewModel> {
        return mWallApi[WallGetRequestModel(ApiConstants.MY_GROUP_ID, count, offset).toMap()]
            .flatMap { full -> Observable.fromIterable(VkListHelper.getWallList(full.response!!)) }
            .compose(applyFilter())
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
        return Observable.fromCallable<List<WallItem>>(getListFromRealmCallable())
            .flatMap { source -> Observable.fromIterable(source) }
            .compose(applyFilter())
            .flatMap { wallItem -> Observable.fromIterable(parsePojoModel(wallItem)) }
    }

    private fun parsePojoModel(wallItem: WallItem): MutableList<BaseViewModel> {
        val baseItems: MutableList<BaseViewModel> = ArrayList()
        baseItems.add(NewsItemHeaderViewModel(wallItem))
        baseItems.add(NewsItemBodyViewModel(wallItem))
        baseItems.add(NewsItemFooterViewModel(wallItem))
        return baseItems
    }

    fun getListFromRealmCallable(): Callable<List<WallItem>> {

        return  Callable {
            val sortFields = arrayOf("date")
            val sortOrder = arrayOf(Sort.DESCENDING)
            val realm = Realm.getDefaultInstance()
            val realmResults = realm.where(WallItem::class.java)
                .sort(sortFields, sortOrder).findAll()
            return@Callable realm.copyFromRealm(realmResults)
        }


    }


}


