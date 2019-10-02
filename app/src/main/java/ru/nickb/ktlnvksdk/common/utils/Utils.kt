package ru.nickb.ktlnvksdk.common.utils

import android.content.Context
import com.vk.sdk.api.model.VKAttachments
import ru.nickb.ktlnvksdk.model.attachment.ApiAttachment
import java.text.SimpleDateFormat
import java.util.*


object Utils {

    fun convertAttachmentsToFontIcons(attachments: List<ApiAttachment>): String {
        var attachmentsString = ""

        for (attachment in attachments) {
            when (attachment.getType()) {
                VKAttachments.TYPE_PHOTO -> attachmentsString += String(charArrayOf(0xE251.toChar())) + " "
                VKAttachments.TYPE_AUDIO -> attachmentsString += String(charArrayOf(0xE310.toChar())) + " "
                VKAttachments.TYPE_VIDEO -> attachmentsString += String(charArrayOf(0xE02C.toChar())) + " "
                VKAttachments.TYPE_LINK -> attachmentsString += String(charArrayOf(0xE250.toChar())) + " "
                VKAttachments.TYPE_DOC -> attachmentsString += String(charArrayOf(0xE24D.toChar())) + " "
            }
        }
        return attachmentsString
    }

    fun parseDate(initialDate: Long, context: Context): String {
        val currentlocale = context.getResources().getConfiguration().locale

        val date = Date(initialDate * 1000)

        val calendar = Calendar.getInstance()
        calendar.setTime(date)

        var sdf = SimpleDateFormat("dd.MM.yy в H:mm", currentlocale)

        if (calendar.get(Calendar.DAY_OF_YEAR) === Calendar.getInstance().get(Calendar.DAY_OF_YEAR) && calendar.get(
                Calendar.YEAR
            ) === Calendar.getInstance().get(Calendar.YEAR)
        ) {
            sdf = SimpleDateFormat("сегодня в H:mm", currentlocale)
        } else if (calendar.get(Calendar.YEAR) === Calendar.getInstance().get(Calendar.YEAR)) {
            sdf = SimpleDateFormat("d MMM в H:mm", currentlocale)
        }
        return sdf.format(date)
    }


}