package ru.nickb.ktlnvksdk.mvp.presenter

import com.arellomobile.mvp.InjectViewState
import io.reactivex.Observable
import io.realm.Realm
import io.realm.RealmResults
import io.realm.Sort
import ru.nickb.ktlnvksdk.MyApplication
import ru.nickb.ktlnvksdk.consts.ApiConstants
import ru.nickb.ktlnvksdk.model.Member
import ru.nickb.ktlnvksdk.model.Topic
import ru.nickb.ktlnvksdk.model.view.BaseViewModel
import ru.nickb.ktlnvksdk.model.view.TopicViewModel
import ru.nickb.ktlnvksdk.mvp.view.BaseFeedView
import ru.nickb.ktlnvksdk.rest.api.BoardApi
import ru.nickb.ktlnvksdk.rest.model.request.BoardGetTopicsRequestModel
import java.util.concurrent.Callable
import javax.inject.Inject





@InjectViewState
class BoardPresenter: BaseFeedPresenter<BaseFeedView>() {

    @Inject
    lateinit var mBoardApi:BoardApi

    init {
        MyApplication.sApplicationComponent.inject(this)
    }


    override fun onCreateLoadDataObservable(count: Int, offset: Int): Observable<BaseViewModel> {
        return mBoardApi.getTopics(BoardGetTopicsRequestModel(ApiConstants.MY_GROUP_ID, count, offset).toMap())
            .flatMap<Topic> { baseItemResponseFull -> Observable.fromIterable<Topic>(baseItemResponseFull.response!!.items) }
            .doOnNext { topic -> topic.groupid = ApiConstants.MY_GROUP_ID }
            .doOnNext { this.saveToDb(it) }
            .map { TopicViewModel(it)  }
    }



    override fun onCreateRestoreDataObservable(): Observable<BaseViewModel> {
        return Observable.fromCallable(getListFromRealmCallable())
            .flatMap<Any> { Observable.fromIterable(it) }
            .map { TopicViewModel() }
    }

    fun getListFromRealmCallable(): Callable<List<Topic>> {
        return Callable {
            val sortFields = arrayOf(Member.ID)
            val sortOrder = arrayOf(Sort.DESCENDING)
            val realm = Realm.getDefaultInstance()
            val results: RealmResults<Topic> = realm.where(Topic::class.java)
                .equalTo("groupId", ApiConstants.MY_GROUP_ID)
                .sort(sortFields, sortOrder).findAll()
            return@Callable realm.copyFromRealm(results)
        }
    }

}