package io.github.ldhnam.roomrxjava2.mvp.base


interface Presenter<in V: MvpView> {
    fun attachView(mvpView: V)

    fun detachView()
}