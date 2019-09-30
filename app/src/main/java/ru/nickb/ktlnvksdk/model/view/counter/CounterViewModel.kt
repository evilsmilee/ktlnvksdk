package ru.nickb.ktlnvksdk.model.view.counter

import ru.nickb.ktlnvksdk.R


open class CounterViewModel(count: Int) {

    var count: Int = 0
        protected set
    var iconColor = R.color.colorIconDis
        protected set
    var textColor = R.color.colorIconDis
        protected set

    init {
        this.count = count
        if (this.count > 0) {
            setDefaultColor()
        } else {
            setDisabledColor()
        }
    }

    private fun setDisabledColor() {
        iconColor = R.color.colorIconDis
        textColor = R.color.colorIconDis
    }

    private fun setDefaultColor() {
        iconColor = R.color.colorIcon
        textColor = R.color.colorIcon
    }

    protected fun setAccentColor() {
        iconColor = R.color.colorAccent
        textColor = R.color.colorAccent
    }
}