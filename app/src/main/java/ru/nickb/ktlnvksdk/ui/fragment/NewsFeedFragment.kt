package ru.nickb.ktlnvksdk.ui.fragment

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ru.nickb.ktlnvksdk.MyApplication
import ru.nickb.ktlnvksdk.R
import ru.nickb.ktlnvksdk.common.utils.VkListHelper
import ru.nickb.ktlnvksdk.model.view.BaseViewModel
import ru.nickb.ktlnvksdk.model.view.NewsItemBodyViewModel
import ru.nickb.ktlnvksdk.model.view.NewsItemFooterViewModel
import ru.nickb.ktlnvksdk.model.view.NewsItemHeaderViewModel
import ru.nickb.ktlnvksdk.rest.api.WallApi
import ru.nickb.ktlnvksdk.rest.model.request.WallGetRequestModel
import ru.nickb.ktlnvksdk.rest.model.response.WallGetResponse
import javax.inject.Inject


class NewsFeedFragment : BaseFeedFragment() {

    @Inject
    lateinit var mWallApi: WallApi


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        MyApplication.sApplicationComponent.inject(this)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Log.i("asd", "asd1")
        mWallApi[WallGetRequestModel(-86529522).toMap()].enqueue(object :
            Callback<WallGetResponse> {
            override fun onResponse(call: Call<WallGetResponse>, response: Response<WallGetResponse>) {
                Log.i("asd", "asd2")
                val wallItems = VkListHelper.getWallList(response.body()!!.response!!)
                val list: MutableList<BaseViewModel> = arrayListOf()
                Log.i("asd", response.body()!!.response!!.count.toString())
                for (item in wallItems) {
                    list.add(NewsItemHeaderViewModel(item))
                    list.add(NewsItemBodyViewModel(item))
                    list.add(NewsItemFooterViewModel(item))
                }
                mAdapter.addItems(list)
                Toast.makeText(
                    activity,
                    "Likes: " + response.body()!!.response?.items?.get(0)?.likes?.count,
                    Toast.LENGTH_LONG
                ).show()
            }

            override fun onFailure(call: Call<WallGetResponse>, t: Throwable) {
                t.printStackTrace()
                Log.i("asd", t.localizedMessage)
            }
        })
    }


    override fun onCreateToolbarTitle(): Int {
        return R.string.screen_name_news
    }
}// Required empty public constructor