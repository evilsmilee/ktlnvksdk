package ru.nickb.ktlnvksdk.rest.model.response

import ru.nickb.ktlnvksdk.model.Group
import ru.nickb.ktlnvksdk.model.Owner
import ru.nickb.ktlnvksdk.model.Profile
import kotlin.math.abs


class ItemWithSenderResponse<T> : BaseItemResponse<T>() {

    private val profiles: List<Profile> =  ArrayList()
    private val groups: List<Group> = ArrayList()

    private fun getProfiles(): List<Profile> {
        return profiles
    }

    private fun getGroups(): List<Group> {
        return groups
    }

    private fun getAllSenders(): List<Owner> {
        val all: MutableList<Owner> = ArrayList()
        all.addAll(getProfiles())
        all.addAll(getGroups())
        return all
    }

    fun getSender(id: Int): Owner? {
        for (owner: Owner in getAllSenders()) {
            if (owner.id === abs(id)) {
                return owner
            }
        }
        return null
    }
}