package ru.nickb.ktlnvksdk.model


import com.google.gson.annotations.SerializedName

data class WallItem(
    @SerializedName("id") val id: Int, @SerializedName("from_id") val from_id: Int, @SerializedName(
        "owner_id"
    ) val owner_id: Int, @SerializedName("date") val date: Int, @SerializedName("marked_as_ads") val marked_as_ads: Int, @SerializedName(
        "post_type"
    ) val post_type: String, @SerializedName("text") val text: String, @SerializedName("can_pin") val can_pin: Int, @SerializedName(
        "attachments"
    ) val attachments: List<Attachments>, @SerializedName("post_source") val post_source: PostSource, @SerializedName(
        "comments"
    ) val comments: Comments, @SerializedName("likes") val likes: Likes, @SerializedName("reposts") val reposts: Reposts, @SerializedName(
        "views"
    ) val views: Views


)