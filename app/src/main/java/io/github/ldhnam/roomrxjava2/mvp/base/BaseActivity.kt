package io.github.ldhnam.roomrxjava2.mvp.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import io.github.ldhnam.roomrxjava2.App
import io.github.ldhnam.roomrxjava2.di.component.ActivityComponent
import io.github.ldhnam.roomrxjava2.di.component.ConfigPersistentComponent
import io.github.ldhnam.roomrxjava2.di.component.DaggerConfigPersistentComponent
import io.github.ldhnam.roomrxjava2.di.module.ActivityModule
import me.henrytao.mdcore.core.MdCore


abstract class BaseActivity: AppCompatActivity() {

    private var mActivityComponent: ActivityComponent? = null

    abstract val layout: Int

    override fun onCreate(savedInstanceState: Bundle?) {
        MdCore.init(this)
        super.onCreate(savedInstanceState)
        setContentView(layout)
        val configPersistentComponent: ConfigPersistentComponent = DaggerConfigPersistentComponent.builder()
                .appComponent(App[this].component)
                .build()
        mActivityComponent = configPersistentComponent.activityComponent(ActivityModule(this))
        mActivityComponent?.inject(this)
    }

    fun activityComponent(): ActivityComponent {
        return mActivityComponent as ActivityComponent
    }

}