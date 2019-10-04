package ru.nickb.ktlnvksdk.model.view

import android.view.View
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import com.bumptech.glide.Glide
import de.hdodenhof.circleimageview.CircleImageView
import ru.nickb.ktlnvksdk.R
import ru.nickb.ktlnvksdk.model.Member
import ru.nickb.ktlnvksdk.ui.holder.BaseViewHolder


class MemberViewModel : BaseViewModel {


    var id: Int = 0

    var groupId: Int = 0

    var photo: String? = null

    var fullName: String? = null

    override val type: LayoutTypes
        get() = LayoutTypes.Member

    constructor() {

    }

    constructor(member: Member) {
        this.id = member.id
        this.groupId = member.groupId

        this.photo = member.photo
        this.fullName = member.fullName
    }

    override fun onCreateViewHolder(view: View): BaseViewHolder<*> {
        return MemberViewHolder(view)
    }


    internal class MemberViewHolder(itemView: View) : BaseViewHolder<MemberViewModel>(itemView) {

        @BindView(R.id.civ_profile_image)
        lateinit var civProfilePhoto: CircleImageView

        @BindView(R.id.tv_profile_name)
        lateinit var civProfileName: TextView


        init {

            ButterKnife.bind(this, itemView)
        }

        override  fun bindViewHolder(memberViewModel: MemberViewModel) {
            val context = itemView.context

            Glide.with(context)
                .load(memberViewModel.photo)
                .into(civProfilePhoto!!)
            civProfileName!!.text = memberViewModel.fullName
        }


        override fun unbindViewHolder() {
            civProfileName!!.text = null
            civProfilePhoto!!.setImageBitmap(null)
        }
    }
}