package ru.nickb.ktlnvksdk.ui.fragment


import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.nickb.ktlnvksdk.R
import ru.nickb.ktlnvksdk.common.manager.BaseAdapter


abstract class BaseFeedFragment : BaseFragment() {

    lateinit var mRecyclerView: RecyclerView

    lateinit var mAdapter: BaseAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpRecyclerView(view)
        setUpAdapter(mRecyclerView)
    }

    private fun setUpRecyclerView(rootView: View) {
        mRecyclerView = rootView.findViewById(R.id.rv_list)
        mRecyclerView.layoutManager = LinearLayoutManager(context)
    }

    protected fun setUpAdapter(rv: RecyclerView) {
        mAdapter = BaseAdapter()
        rv.adapter = mAdapter
    }

    override fun getMainContentLayout(): Int {
        return R.layout.fragment_feed
    }

    override fun onCreateToolbarTitle(): Int {
        return 0
    }
}