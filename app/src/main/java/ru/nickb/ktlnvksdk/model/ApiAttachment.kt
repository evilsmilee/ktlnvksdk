package ru.nickb.ktlnvksdk.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

import com.vk.sdk.api.model.VKAttachments
import ru.nickb.ktlnvksdk.model.attachment.*


class ApiAttachment {

    @SerializedName("type")
    @Expose
    private var type: String? = null
    @SerializedName("photo")
    @Expose
    private var photo: ru.nickb.ktlnvksdk.model.attachment.Photo? = null

    private var audio: Audio? = null
    private var video: Video? = null
    private var doc: Doc? = null
    private var link: Link? = null
    private var page: Page? = null

    fun getAttachment(): Attachment? {
        when (type) {
            VKAttachments.TYPE_PHOTO -> return photo
            VKAttachments.TYPE_AUDIO -> return audio
            VKAttachments.TYPE_VIDEO -> return video
            VKAttachments.TYPE_DOC -> return doc
            VKAttachments.TYPE_LINK -> return link
            VKAttachments.TYPE_WIKI_PAGE -> return page
            else -> throw NoSuchElementException("Attachment type $type is not supported")
        }
    }

    fun setAudio(audio: Audio) {
        this.audio = audio
    }

    fun setVideo(video: Video) {
        this.video = video
    }

    fun setDoc(doc: Doc) {
        this.doc = doc
    }

    fun setLink(link: Link) {
        this.link = link
    }

    fun setPage(page: Page) {
        this.page = page
    }

    fun getAudio(): Audio? {
        return audio
    }

    fun getVideo(): Video? {
        return video
    }

    fun getDoc(): Doc? {
        return doc
    }

    fun getLink(): Link? {
        return link
    }

    fun getPage(): Page? {
        return page
    }

    fun getType(): String? {
        return type
    }

    fun setType(type: String) {
        this.type = type
    }

    fun getPhoto(): ru.nickb.ktlnvksdk.model.attachment.Photo? {
        return photo
    }

    fun setPhoto(photo: ru.nickb.ktlnvksdk.model.attachment.Photo?) {
        this.photo = photo
    }

}
