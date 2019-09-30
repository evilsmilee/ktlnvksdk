package ru.nickb.ktlnvksdk.common.utils

import ru.nickb.ktlnvksdk.model.Owner
import ru.nickb.ktlnvksdk.model.WallItem
import ru.nickb.ktlnvksdk.rest.model.response.ItemWithSenderResponse

object VkListHelper {

    fun getWallList(response: ItemWithSenderResponse<WallItem>): List<WallItem> {
        val wallItems: List<WallItem> = response.items

        for(wallItem: WallItem in wallItems) {
            val sender: Owner = response.getSender(wallItem.fromId!!) as Owner
            wallItem.senderName = sender.getFullName()
            wallItem.senderPhoto = sender.getPhoto()
            wallItem.attachmentsString = Utils.convertAttachmentsToFontIcons(wallItem.attachments)

            if (wallItem.haveSharedRepost()) {
                val repostSender: Owner = response.getSender(wallItem.getSharedRepost()?.fromId!!)!!
                wallItem.getSharedRepost()!!.senderName = repostSender.getFullName()
                wallItem.getSharedRepost()!!.senderPhoto = repostSender.getPhoto()
                wallItem.getSharedRepost()!!.attachmentsString = Utils.convertAttachmentsToFontIcons(wallItem.getSharedRepost()!!.attachments)
            }

        }

        return wallItems
    }

}