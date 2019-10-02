package ru.nickb.ktlnvksdk.ui.holder

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import com.bumptech.glide.Glide
import de.hdodenhof.circleimageview.CircleImageView
import ru.nickb.ktlnvksdk.R
import ru.nickb.ktlnvksdk.model.view.NewsItemHeaderViewModel



class NewsItemHeaderHolder(itemView: View) : BaseViewHolder<NewsItemHeaderViewModel>(itemView) {


   /* private  var civProfileImage: CircleImageView = itemView.findViewById(R.id.civ_profile_image)
    private var tvName: TextView = itemView.findViewById(R.id.tv_profile_name)
    private var ivRepostedIcon: ImageView = itemView.findViewById(R.id.iv_reposted_icon)
    private var tvRepostedProfileName: TextView = itemView.findViewById(R.id.tv_reposted_profile_name)*/

    @BindView(R.id.civ_profile_image)
    lateinit var civProfileImage: CircleImageView

    @BindView(R.id.tv_profile_name)
    lateinit var tvName: TextView

    @BindView(R.id.iv_reposted_icon)
    lateinit var ivRepostedIcon: ImageView

    @BindView(R.id.tv_reposted_profile_name)
    lateinit var tvRepostedProfileName: TextView

    init {
        ButterKnife.bind(this, itemView)
    }

    override fun bindViewHolder(item: NewsItemHeaderViewModel) {
        val context: Context = itemView.context
        Glide.with(context)
            .load(item.getProfilePhoto())
            .into(civProfileImage)
        tvName.text = item.getProfileName()

        if (item.isRepost()) {
            ivRepostedIcon.visibility = View.VISIBLE
            tvRepostedProfileName.text = item.getProfileName()
        } else {
            ivRepostedIcon.visibility = View.GONE
            tvRepostedProfileName.text = null
        }

    }

    override fun unbindViewHolder() {
        civProfileImage.setImageBitmap(null)
        tvName.text = null
        tvRepostedProfileName.text = null
    }

}