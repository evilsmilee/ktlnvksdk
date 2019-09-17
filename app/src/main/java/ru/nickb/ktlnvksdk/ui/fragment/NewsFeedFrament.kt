package ru.nickb.ktlnvksdk.ui.fragment

import android.os.Bundle
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ru.nickb.ktlnvksdk.CurrentUser
import ru.nickb.ktlnvksdk.R
import ru.nickb.ktlnvksdk.rest.api.WallApi
import ru.nickb.ktlnvksdk.rest.model.response.BaseItemResponse
import ru.nickb.ktlnvksdk.rest.model.response.Full
import javax.inject.Inject


class NewsFeedFrament: BaseFragment() {

    @Inject
    lateinit var mWallApi: WallApi

    override fun getMainContentLayout(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCreateToolbarTitle(): Int {
      return  R.string.screen_name_news
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        mWallApi.get("-86529522", CurrentUser.accessToken!!, 1, "5.67")
            .enqueue(object : Callback<Full<BaseItemResponse<*>>> {
                override fun onResponse(
                    call: Call<Full<BaseItemResponse<*>>>,
                    response: Response<Full<BaseItemResponse<*>>>
                ) {
                    Toast.makeText(
                        activity,
                        "Count: " + response.body()?.response?.count,
                        Toast.LENGTH_LONG
                    ).show()
                }

                override fun onFailure(call: Call<Full<BaseItemResponse<*>>>, t: Throwable) {
                    t.printStackTrace()
                }
            })
    }


}