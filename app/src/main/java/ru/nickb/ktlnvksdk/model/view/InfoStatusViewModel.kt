package ru.nickb.ktlnvksdk.model.view

import android.view.View
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import ru.nickb.ktlnvksdk.R
import ru.nickb.ktlnvksdk.model.Group
import ru.nickb.ktlnvksdk.ui.holder.BaseViewHolder


class InfoStatusViewModel: BaseViewModel {


    private var mStatus: String = ""

    private var mDescription: String = ""

    private var mSite: String = ""


    constructor(group: Group) {
        this.mStatus = group.getStatus()

        this.mDescription = group.getDescription()

        this.mSite = group.getSite()
    }



    override val type: LayoutTypes
        get() = LayoutTypes.InfoStatus



    override fun onCreateViewHolder(view: View): InfoStatusViewHolder {
        return InfoStatusViewHolder(view)
    }


    fun getStatus(): String {
        return mStatus
    }

    fun getDescription(): String {
        return mDescription
    }

    fun getSite(): String {
        return mSite
    }

    class InfoStatusViewHolder(itemView: View) :
        BaseViewHolder<InfoStatusViewModel>(itemView) {

        @BindView(R.id.tv_status_text)
        lateinit var tvStatusText: TextView

        @BindView(R.id.tv_description_text)
        lateinit var tvDescriptionText: TextView

        @BindView(R.id.tv_site_text)
        lateinit var tvSiteText: TextView


        init {

            ButterKnife.bind(this, itemView)
        }

       override fun bindViewHolder(infoStatusViewModel: InfoStatusViewModel) {
            tvStatusText.text = infoStatusViewModel.getStatus()
            tvDescriptionText.text = infoStatusViewModel.getDescription()
            tvSiteText.text = infoStatusViewModel.getSite()
        }

        override  fun unbindViewHolder() {
            tvStatusText.text = null
            tvDescriptionText.text = null
            tvSiteText.text = null
        }
    }

}