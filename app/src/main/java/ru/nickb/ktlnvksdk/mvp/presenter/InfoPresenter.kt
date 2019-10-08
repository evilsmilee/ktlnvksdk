package ru.nickb.ktlnvksdk.mvp.presenter

import com.arellomobile.mvp.InjectViewState
import io.reactivex.Observable
import io.reactivex.functions.Consumer
import io.realm.Realm
import ru.nickb.ktlnvksdk.MyApplication
import ru.nickb.ktlnvksdk.consts.ApiConstants
import ru.nickb.ktlnvksdk.model.Group
import ru.nickb.ktlnvksdk.model.view.BaseViewModel
import ru.nickb.ktlnvksdk.model.view.InfoContactsViewModel
import ru.nickb.ktlnvksdk.model.view.InfoLinksViewModel
import ru.nickb.ktlnvksdk.model.view.InfoStatusViewModel
import ru.nickb.ktlnvksdk.mvp.view.BaseFeedView
import ru.nickb.ktlnvksdk.rest.api.GroupsApi
import ru.nickb.ktlnvksdk.rest.model.request.GroupsGetByIdRequestModel
import java.util.concurrent.Callable
import javax.inject.Inject


@InjectViewState
class InfoPresenter: BaseFeedPresenter<BaseFeedView>() {

    @Inject
    lateinit var mGroupApi: GroupsApi


    init {
        MyApplication.sApplicationComponent.inject(this)
    }




   override fun onCreateLoadDataObservable(count: Int, offset: Int): Observable<BaseViewModel> {
        return mGroupApi.getById(GroupsGetByIdRequestModel(ApiConstants.MY_GROUP_ID).toMap())
            .flatMap<Group> {listFull -> Observable.fromIterable<Group>(listFull.response)}
            .doOnNext(Consumer<Group> { item -> this@InfoPresenter.saveToDb(item) })
            .flatMap { group -> Observable.fromIterable(parsePojoModel(group)) }
    }



    override fun onCreateRestoreDataObservable(): Observable<BaseViewModel> {
        return Observable.fromCallable (getListFromCallable())
            .flatMap { group -> Observable.fromIterable(parsePojoModel(group)) }
    }

    private fun parsePojoModel(group: Group): List<BaseViewModel> {
        val items: MutableList<BaseViewModel> = ArrayList()
        items.add(InfoStatusViewModel(group))
        items.add(InfoContactsViewModel())
        items.add(InfoLinksViewModel())
        return items
    }

    fun getListFromCallable(): Callable<Group> {
        return Callable {
            val realm = Realm.getDefaultInstance()
            val result = realm.where(Group::class.java)
                .equalTo("id", Math.abs(ApiConstants.MY_GROUP_ID))
                .findFirst()
            return@Callable realm.copyFromRealm(result!!)
        }
    }
}