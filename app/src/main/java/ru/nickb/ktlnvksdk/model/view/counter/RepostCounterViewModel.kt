package ru.nickb.ktlnvksdk.model.view.counter

import ru.nickb.ktlnvksdk.model.Reposts


class RepostCounterViewModel(val reposts: Reposts) : CounterViewModel(reposts.count!!) {

    init {
        if (this.reposts.userReposted == 1) {
            setAccentColor()
        }
    }
}