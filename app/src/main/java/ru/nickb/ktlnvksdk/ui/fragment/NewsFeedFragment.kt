package ru.nickb.ktlnvksdk.ui.fragment

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ru.nickb.ktlnvksdk.MyApplication
import ru.nickb.ktlnvksdk.R
import ru.nickb.ktlnvksdk.common.BaseAdapter
import ru.nickb.ktlnvksdk.model.WallItem
import ru.nickb.ktlnvksdk.model.view.NewsFeedItemBodyViewModel

import ru.nickb.ktlnvksdk.rest.api.WallApi
import ru.nickb.ktlnvksdk.rest.model.request.WallGetRequestModel
import ru.nickb.ktlnvksdk.rest.model.response.WallGetResponse
import java.util.*
import javax.inject.Inject


class NewsFeedFragment: BaseFragment() {

    @Inject
    lateinit var mWallApi: WallApi

    lateinit var mRecyclerView: RecyclerView

    lateinit var mAdapter: BaseAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MyApplication.sApplicationComponent.inject(this)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecyclerView(view)
        setUpAdapter(mRecyclerView)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mWallApi.get(WallGetRequestModel(-86529522).toMap())
            .enqueue(object : WallGetResponse(), Callback<WallGetResponse> {
                override fun onResponse(call: Call<WallGetResponse>, response: Response<WallGetResponse>) {
                    val list = ArrayList<NewsFeedItemBodyViewModel>()
                    for(item: WallItem in response.body()?.response?.items!!) {
                        list.add(NewsFeedItemBodyViewModel(item))
                    }
                    mAdapter.addItems(list)
                    Toast.makeText(
                        activity,
                        "Count: " + response.body()?.response?.items?.get(0)?.likes?.count,
                        Toast.LENGTH_LONG
                    ).show()
                }

                override fun onFailure(call: Call<WallGetResponse>, t: Throwable) {
                    t.message
                }


            })
    }

    private fun setUpRecyclerView(rootView: View) {
        mRecyclerView = rootView.findViewById(R.id.rv_list)
        mRecyclerView.layoutManager = LinearLayoutManager(context)
    }

    protected fun setUpAdapter(rv: RecyclerView) {
        rv.adapter = mAdapter
    }

    override fun getMainContentLayout(): Int {
        return R.layout.fragment_feed
    }

    override fun onCreateToolbarTitle(): Int {
        return  R.string.screen_name_news
    }

}