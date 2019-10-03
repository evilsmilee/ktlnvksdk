package ru.nickb.ktlnvksdk.mvp.presenter

import io.reactivex.Observable
import io.realm.Realm
import io.realm.Sort
import ru.nickb.ktlnvksdk.MyApplication
import ru.nickb.ktlnvksdk.consts.ApiConstants
import ru.nickb.ktlnvksdk.model.Member
import ru.nickb.ktlnvksdk.model.view.BaseViewModel
import ru.nickb.ktlnvksdk.model.view.GroupsGetMembersRequestModel
import ru.nickb.ktlnvksdk.model.view.MemberViewModel
import ru.nickb.ktlnvksdk.mvp.view.BaseFeedView
import ru.nickb.ktlnvksdk.rest.api.GroupsApi
import ru.nickb.ktlnvksdk.rest.model.response.BaseItemResponse
import ru.nickb.ktlnvksdk.rest.model.response.Full
import java.util.concurrent.Callable
import javax.inject.Inject

class MembersPresenter: BaseFeedPresenter<BaseFeedView>() {

    @Inject
    lateinit var mGroupApi: GroupsApi

    init {
        MyApplication.sApplicationComponent.inject(this)
    }

    override fun onCreateLoadDataObservable(count: Int, offset: Int): Observable<BaseViewModel> {
        return mGroupApi.getMembers(GroupsGetMembersRequestModel(ApiConstants.MY_GROUP_ID, count, offset).toMap())
            .flatMap{ baseItemResponseFull: Full<BaseItemResponse<Member>> -> return@flatMap Observable.fromIterable(baseItemResponseFull.response?.items) }
            .doOnNext{member -> saveToDb(member) }
            .map { member -> MemberViewModel(member = member) }

    }

    override fun onCreateRestoreDataObservable(): Observable<BaseViewModel> {
        return Observable.fromCallable(getListFromRealmCallable())
            .flatMap {source -> Observable.fromIterable(source)}
            .map { member -> MemberViewModel(member)  }


    }

    fun getListFromRealmCallable(): Callable<List<Member>> {
        return Callable {
            val sortFields = Member.ID
            val sortOrder = Sort.ASCENDING
            val realm = Realm.getDefaultInstance()
            val results = realm.where(Member::class.java)
                .sort(sortFields, sortOrder).findAll()
            return@Callable realm.copyFromRealm(results)

        }
    }
}