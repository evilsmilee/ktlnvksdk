package ru.nickb.ktlnvksdk.model.view

import android.view.View
import android.widget.RelativeLayout
import butterknife.BindView
import butterknife.ButterKnife
import ru.nickb.ktlnvksdk.R
import ru.nickb.ktlnvksdk.ui.holder.BaseViewHolder

class InfoLinksViewModel: BaseViewModel() {



    override val type: LayoutTypes
        get() = LayoutTypes.InfoLinks

    override fun onCreateViewHolder(view: View): InfoLinksViewHolder {
        return InfoLinksViewHolder(view)
    }



    class InfoLinksViewHolder(itemView: View): BaseViewHolder<InfoLinksViewModel>(itemView) {

        @BindView(R.id.rv_links)
        lateinit var rvLinks: RelativeLayout

        init {
            ButterKnife.bind(this, itemView)
        }

        override fun bindViewHolder(item: InfoLinksViewModel) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun unbindViewHolder() {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

    }
}