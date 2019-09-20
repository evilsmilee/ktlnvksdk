package ru.nickb.ktlnvksdk.model
import com.google.gson.annotations.SerializedName



data class Reposts (

	@SerializedName("count") val count : Int,
	@SerializedName("user_reposted") val user_reposted : Int
)