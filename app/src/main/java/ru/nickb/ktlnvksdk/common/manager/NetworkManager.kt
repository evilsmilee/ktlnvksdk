package ru.nickb.ktlnvksdk.common.manager

import android.content.Context
import android.net.ConnectivityManager
import io.reactivex.Observable
import io.realm.internal.Util
import ru.nickb.ktlnvksdk.MyApplication
import java.net.HttpURLConnection
import java.net.URL
import java.util.concurrent.Callable
import javax.inject.Inject


class NetworkManager {

    @Inject
    lateinit var mContext: Context

    companion object {
        const val TAG = "NetworkManager"
    }

    init {
        MyApplication.sApplicationComponent.inject(this)
    }

    fun isOnline(): Boolean {
        val cm = mContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = cm.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnected || Util.isEmulator()
    }

    fun isVkReachableCallable(): Callable<Boolean> {
        return object : Callable<Boolean> {
            override fun call(): Boolean? {
                try {
                    if (!isOnline()) {
                        return false
                    }

                    val url = URL("https://api.vk.com")
                    val urlc = url.openConnection() as HttpURLConnection
                    urlc.setConnectTimeout(2000)
                    urlc.connect()

                    return true

                } catch (e: Exception) {
                    return false
                }

            }
        }
    }

    fun getNetworkObservable(): Observable<Boolean> {
        return Observable.fromCallable(isVkReachableCallable())
    }
}