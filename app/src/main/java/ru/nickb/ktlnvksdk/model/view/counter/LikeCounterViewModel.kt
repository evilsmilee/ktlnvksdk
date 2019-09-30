package ru.nickb.ktlnvksdk.model.view.counter

import ru.nickb.ktlnvksdk.model.Likes


class LikeCounterViewModel(val likes: Likes) : CounterViewModel(likes.count!!) {

    init {

        if (this.likes.userLikes == 1) {
            setAccentColor()
        }
    }
}