package ru.nickb.ktlnvksdk.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ru.nickb.ktlnvksdk.MyApplication
import ru.nickb.ktlnvksdk.R
import ru.nickb.ktlnvksdk.rest.api.WallApi
import ru.nickb.ktlnvksdk.rest.model.request.WallGetRequestModel
import ru.nickb.ktlnvksdk.rest.model.response.WallGetResponse
import javax.inject.Inject


class NewsFeedFragment: BaseFragment() {

    @Inject
    lateinit var mWallApi: WallApi

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MyApplication.sApplicationComponent.inject(this)
        Log.i("Okay", "step4")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Log.i("Okay", "step5")
        mWallApi.get(WallGetRequestModel(-86529522).toMap())
            .enqueue(object : WallGetResponse(), Callback<WallGetResponse> {
                override fun onResponse(
                    call: Call<WallGetResponse>,
                    response: Response<WallGetResponse>
                ) {
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



    override fun getMainContentLayout(): Int {
        return R.layout.fragment_feed
    }

    override fun onCreateToolbarTitle(): Int {
        return  R.string.screen_name_news
    }

}