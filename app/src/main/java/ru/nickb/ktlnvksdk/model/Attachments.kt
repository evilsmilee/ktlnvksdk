package ru.nickb.ktlnvksdk.model
import com.google.gson.annotations.SerializedName




data class Attachments (

	@SerializedName("type") val type : String,
	@SerializedName("photo") val photo : Photo
)