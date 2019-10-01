package ru.nickb.ktlnvksdk.ui.fragment


import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import ru.nickb.ktlnvksdk.R
import ru.nickb.ktlnvksdk.common.manager.BaseAdapter
import ru.nickb.ktlnvksdk.model.view.BaseViewModel
import ru.nickb.ktlnvksdk.mvp.presenter.BaseFeedPresenter
import ru.nickb.ktlnvksdk.mvp.view.BaseFeedView


abstract class BaseFeedFragment : BaseFragment(), BaseFeedView {


    lateinit var mRecyclerView: RecyclerView

    lateinit var mAdapter: BaseAdapter

    lateinit var mSwipeRefreshLayout: SwipeRefreshLayout
    lateinit var mProgressBar: ProgressBar


     lateinit var mBaseFeedPresenter: BaseFeedPresenter<BaseFeedView>



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpSwipeToRefreshLayout(view)
        setUpRecyclerView(view)
        setUpAdapter(mRecyclerView)
        mBaseFeedPresenter = onCreateFeedPresenter()
        mBaseFeedPresenter.loadStart()
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

    private fun setUpSwipeToRefreshLayout(view: View) {
        mSwipeRefreshLayout = view.findViewById(R.id.swipe_refresh)
        mSwipeRefreshLayout.setOnRefreshListener{onCreateFeedPresenter().loadRefresh()}
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorAccent)
        mProgressBar = getBaseActivity().getProgressBar()


    }

    override fun showRefreshing() {

    }

    override fun hideRefreshing() {
        mSwipeRefreshLayout.isRefreshing = false
    }

    override fun showListProgress() {
       mProgressBar.visibility = View.VISIBLE
    }

    override fun hideListProgress() {
        mProgressBar.visibility = View.GONE
    }

    override fun showError(message: String) {
        Toast.makeText(getBaseActivity(), message, Toast.LENGTH_LONG).show()
    }

    override fun setItems(items: MutableList<BaseViewModel>) {
        mAdapter.setItems(items)
    }

    override fun addItems(items: MutableList<BaseViewModel>) {
        mAdapter.addItems(items)
    }

    protected abstract fun onCreateFeedPresenter(): BaseFeedPresenter<BaseFeedView>

}