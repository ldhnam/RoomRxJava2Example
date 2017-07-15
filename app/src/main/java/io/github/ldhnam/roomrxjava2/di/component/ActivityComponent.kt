package io.github.ldhnam.roomrxjava2.di.component

import dagger.Subcomponent
import io.github.ldhnam.roomrxjava2.mvp.activity.MainActivity
import io.github.ldhnam.roomrxjava2.di.module.ActivityModule
import io.github.ldhnam.roomrxjava2.di.scope.ActivityScope
import io.github.ldhnam.roomrxjava2.mvp.base.BaseActivity


@ActivityScope
@Subcomponent(modules = arrayOf(ActivityModule::class))
interface ActivityComponent {
    fun inject(baseActivity: BaseActivity)
    fun inject(mainActivity: MainActivity)

}