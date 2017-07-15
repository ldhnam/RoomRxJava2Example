package io.github.ldhnam.roomrxjava2.mvp.view

import io.github.ldhnam.roomrxjava2.data.model.User
import io.github.ldhnam.roomrxjava2.mvp.base.MvpView


interface MainView: MvpView {
    fun showUsers(users: ArrayList<User>)
}