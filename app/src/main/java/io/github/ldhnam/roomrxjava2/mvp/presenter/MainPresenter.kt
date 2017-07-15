package io.github.ldhnam.roomrxjava2.mvp.presenter

import io.github.ldhnam.roomrxjava2.data.local.AppDatabase
import io.github.ldhnam.roomrxjava2.data.model.User
import io.github.ldhnam.roomrxjava2.data.remote.RestService
import io.github.ldhnam.roomrxjava2.di.scope.ConfigPersistent
import io.github.ldhnam.roomrxjava2.mvp.base.BasePresenter
import io.github.ldhnam.roomrxjava2.mvp.view.MainView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@ConfigPersistent
class MainPresenter @Inject constructor(private val mRestService: RestService, private val mAppDatabase: AppDatabase): BasePresenter<MainView>() {

    override fun attachView(mvpView: MainView) {
        super.attachView(mvpView)
    }

    fun getUsersFromNetwork() {
        addDisposable(mRestService.getUsers()
                .map {
                    for (user in it) {
                        mAppDatabase.userDao().insert(user)
                    }
                    it
                }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    users -> mvpView?.showUsers(users)
                }, { throwable -> throwable.printStackTrace() }))
    }

    fun addListUserFromNetwork() {
        addDisposable(mRestService.getUsers()
                .map {
                    mAppDatabase.userDao().insertAll(it)
                    it
                }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    users -> mvpView?.showUsers(users)
                }, { throwable -> throwable.printStackTrace() }))
    }

    fun getUsersFromLocal() {
        val users: ArrayList<User> = ArrayList()
        addDisposable(mAppDatabase.userDao()
                .getAllUser()
                .map {
                    users += it
                    users
                }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    mvpView?.showUsers(users)
                }, { throwable -> throwable.printStackTrace() }))
    }
}