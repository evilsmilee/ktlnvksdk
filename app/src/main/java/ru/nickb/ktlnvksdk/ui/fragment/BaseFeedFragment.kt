package ru.nickb.ktlnvksdk.ui.fragment


import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SimpleItemAnimator
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import butterknife.BindView
import butterknife.ButterKnife
import kotlinx.android.synthetic.main.fragment_feed.*
import ru.nickb.ktlnvksdk.R
import ru.nickb.ktlnvksdk.common.manager.BaseAdapter
import ru.nickb.ktlnvksdk.common.manager.MyLinearLayoutManager
import ru.nickb.ktlnvksdk.model.view.BaseViewModel
import ru.nickb.ktlnvksdk.mvp.presenter.BaseFeedPresenter
import ru.nickb.ktlnvksdk.mvp.view.BaseFeedView





abstract class BaseFeedFragment : BaseFragment(), BaseFeedView {

    @BindView(R.id.rv_list)
    lateinit var mRecyclerView: RecyclerView

    lateinit var mAdapter: BaseAdapter

    @BindView(R.id.swipe_refresh)
    lateinit var mSwipeRefreshLayout: SwipeRefreshLayout
    lateinit var mProgressBar: ProgressBar


     lateinit var mBaseFeedPresenter: BaseFeedPresenter<BaseFeedView>

     var isWithEndlessList: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        isWithEndlessList = true
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ButterKnife.bind(this, view)
        setUpSwipeToRefreshLayout(view)
        setUpRecyclerView(view)
        setUpAdapter(mRecyclerView)
        mBaseFeedPresenter = onCreateFeedPresenter()
        mBaseFeedPresenter.loadStart()
    }

    private fun setUpRecyclerView(rootView: View) {
        val mLinearLayoutManager = MyLinearLayoutManager(activity!!)
        mRecyclerView.layoutManager = mLinearLayoutManager
        if(isWithEndlessList) {
            mRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    if(mLinearLayoutManager.isOnNextPagePosition){
                        mBaseFeedPresenter.loadNext(mAdapter.getRealItemCount())
                    }
                }

            })
        }
        (mRecyclerView.itemAnimator as SimpleItemAnimator).supportsChangeAnimations = false
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
        mSwipeRefreshLayout.setOnRefreshListener{
            onCreateFeedPresenter().loadRefresh()
        swipe_refresh.isRefreshing = false}
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