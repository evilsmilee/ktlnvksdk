package ru.nickb.ktlnvksdk.model.view

import android.view.View
import android.widget.TextView
import androidx.annotation.Nullable
import butterknife.BindView
import butterknife.ButterKnife
import ru.nickb.ktlnvksdk.R
import ru.nickb.ktlnvksdk.model.Topic
import ru.nickb.ktlnvksdk.ui.holder.BaseViewHolder


class TopicViewModel : BaseViewModel {

    var id: Int = 0
    var groupId: Int = 0
    var title: String = ""

    var commentsCount: String = ""


    override val type: LayoutTypes
        get() = LayoutTypes.Topic

    constructor()

    constructor(topic: Topic) {
        this.id = topic.getId()
        this.groupId = topic.groupid

        this.title = topic.title

        this.commentsCount = topic.comments.toString() + " сообщений"
    }

  override fun onCreateViewHolder(view: View): BaseViewHolder<*> {
        return TopicViewHolder(view)
    }


   internal class TopicViewHolder(itemView: View) : BaseViewHolder<TopicViewModel>(itemView) {

        @BindView(R.id.tv_title)
        lateinit var tvTitle: TextView

        @BindView(R.id.tv_comments_count)
        @Nullable
        lateinit var tvCommentsCount: TextView


       init {
           ButterKnife.bind(this, itemView)
       }

       override fun bindViewHolder(topicViewModel: TopicViewModel) {
            tvTitle.text = topicViewModel.title
            tvCommentsCount.text = topicViewModel.commentsCount
        }

       override fun unbindViewHolder() {
            tvTitle.text = null
            tvCommentsCount.text = null
        }
    }
}