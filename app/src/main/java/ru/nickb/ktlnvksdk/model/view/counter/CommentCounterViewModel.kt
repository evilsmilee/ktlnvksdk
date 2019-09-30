package ru.nickb.ktlnvksdk.model.view.counter

import ru.nickb.ktlnvksdk.model.Comments


class CommentCounterViewModel(val comments: Comments) : CounterViewModel(comments.count!!)