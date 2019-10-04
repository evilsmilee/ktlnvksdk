package ru.nickb.ktlnvksdk.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey


open class Profile: Owner, RealmObject() {


    @SerializedName("id")
    @Expose
    @PrimaryKey
    private var id: Int = 0

    @SerializedName("photo_50")
    @Expose
    private var photo50: String? = null

    @SerializedName("photo_100")
    @Expose
    private var photo100: String? = null

    @SerializedName("first_name")
    @Expose
    private var firstName: String? = ""

    @SerializedName("last_name")
    @Expose
    private var lastName: String? = ""

    @SerializedName("sex")
    @Expose
    private var sex: Int = 0

    @SerializedName("screen_name")
    @Expose
    private var screenName: String? = ""


    @SerializedName("online")
    @Expose
    private var online: Int = 0

    @SerializedName("hidden")
    @Expose
    private var hidden: Int = 0


    fun getFirstName(): String? {
        return firstName
    }

    fun setFirstName(firstName: String) {
        this.firstName = firstName
    }

    fun getLastName(): String? {
        return lastName
    }

    fun setLastName(lastName: String) {
        this.lastName = lastName
    }

    fun getSex(): Int {
        return sex
    }

    fun setSex(sex: Int) {
        this.sex = sex
    }

    fun getScreenName(): String? {
        return screenName
    }

    fun setScreenName(screenName: String) {
        this.screenName = screenName
    }

    fun getPhoto50(): String? {
        return photo50
    }

    fun setPhoto50(photo50: String) {
        this.photo50 = photo50
    }

    fun setPhoto100(photo100: String) {
        this.photo100 = photo100
    }

    fun getOnline(): Int {
        return online
    }

    fun setOnline(online: Int) {
        this.online = online
    }

    fun getHidden(): Int {
        return hidden
    }

    fun setHidden(hidden: Int) {
        this.hidden = hidden
    }

    fun isGroup(): Boolean {
        return false
    }

  override  fun getFullName(): String {
        return "$firstName $lastName"
    }

   override fun getPhoto(): String {
        return photo100!!
    }

    fun getDisplayProfilePhoto(): String? {
        return photo100
    }


    override fun getId(): Int {
        return id
    }

}