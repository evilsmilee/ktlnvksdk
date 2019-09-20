package ru.nickb.ktlnvksdk.model
import com.google.gson.annotations.SerializedName



data class Photo (

	@SerializedName("id") val id : Int,
	@SerializedName("album_id") val album_id : Int,
	@SerializedName("owner_id") val owner_id : Int,
	@SerializedName("user_id") val user_id : Int,
	@SerializedName("photo_75") val photo_75 : String,
	@SerializedName("photo_130") val photo_130 : String,
	@SerializedName("photo_604") val photo_604 : String,
	@SerializedName("photo_807") val photo_807 : String,
	@SerializedName("photo_1280") val photo_1280 : String,
	@SerializedName("width") val width : Int,
	@SerializedName("height") val height : Int,
	@SerializedName("text") val text : String,
	@SerializedName("date") val date : Int,
	@SerializedName("access_key") val access_access_key : String
)