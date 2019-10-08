package ru.nickb.ktlnvksdk.model.view

import android.view.View
import android.widget.RelativeLayout
import butterknife.BindView
import butterknife.ButterKnife
import ru.nickb.ktlnvksdk.R
import ru.nickb.ktlnvksdk.ui.holder.BaseViewHolder

class InfoContactsViewModel: BaseViewModel() {


    override val type: LayoutTypes
        get() = LayoutTypes.InfoContacts

    override fun onCreateViewHolder(view: View): InfoContactsViewHolder{
        return InfoContactsViewHolder(view)
    }



    class InfoContactsViewHolder(itemView: View): BaseViewHolder<InfoContactsViewModel>(itemView) {


        @BindView(R.id.rv_contacts)
        lateinit var rvContacts: RelativeLayout

        init {
            ButterKnife.bind(this, itemView)
        }

        override fun bindViewHolder(item: InfoContactsViewModel) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun unbindViewHolder() {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

    }
}