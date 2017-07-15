package io.github.ldhnam.roomrxjava2.mvp.activity

import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.widget.LinearLayout
import io.github.ldhnam.roomrxjava2.R
import io.github.ldhnam.roomrxjava2.common.bindView
import io.github.ldhnam.roomrxjava2.data.model.User
import io.github.ldhnam.roomrxjava2.mvp.adapter.DividerItemDecoration
import io.github.ldhnam.roomrxjava2.mvp.adapter.UserAdapter
import io.github.ldhnam.roomrxjava2.mvp.base.BaseActivity
import io.github.ldhnam.roomrxjava2.mvp.presenter.MainPresenter
import io.github.ldhnam.roomrxjava2.mvp.view.MainView
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : BaseActivity(), MainView {

    val mRvUser by bindView<RecyclerView>(R.id.rv_user)
    val mToolbar by bindView<Toolbar>(R.id.toolbar)

    @Inject lateinit var mMainPresenter: MainPresenter

    lateinit var mUserAdapter: UserAdapter

    override val layout: Int
        get() = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityComponent().inject(this)
        mMainPresenter.attachView(this)

        setSupportActionBar(mToolbar)

        mUserAdapter = UserAdapter()
        mRvUser.layoutManager = LinearLayoutManager(this)
        mRvUser.addItemDecoration(DividerItemDecoration(this, LinearLayout.VERTICAL))
        mRvUser.adapter = mUserAdapter

        if (!isNetworkConnected()) {
            mMainPresenter.getUsersFromLocal()
        } else {
            mMainPresenter.getUsersFromNetwork()
        }
    }

    override fun showUsers(users: ArrayList<User>) {
        mUserAdapter.setUsers(users)
    }

    private fun isNetworkConnected(): Boolean {
        val cm = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = cm.activeNetworkInfo
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting
    }
}
