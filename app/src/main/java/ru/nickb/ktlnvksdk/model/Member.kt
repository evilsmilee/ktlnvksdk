package ru.nickb.ktlnvksdk.model

import com.google.gson.annotations.SerializedName
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class Member : RealmObject {

    companion object {
       const val ID = "id"
       const val GROUP_ID = "group_id"
        const val PHOTO = "photo_100"
        const  val FIRST_NAME = "first_name"
        const  val LAST_NAME = "last_name"
    }

    @PrimaryKey
    @SerializedName(ID)
    var id: Int = 0

    @SerializedName(GROUP_ID)
    var groupId: Int = 0

    @SerializedName(PHOTO)
    var photo: String? = null

    @SerializedName(FIRST_NAME)
    var firstName: String? = null

    @SerializedName(LAST_NAME)
    var lastName: String? = null

    val fullName: String
        get() = "$firstName $lastName"


    constructor() {

    }

    constructor(profile: Profile) {
        this.id = profile.id
        this.photo = profile.getPhoto()
        this.firstName = profile.getFirstName()
        this.lastName = profile.getLastName()
    }


}