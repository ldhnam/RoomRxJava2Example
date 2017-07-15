package io.github.ldhnam.roomrxjava2.di.module

import android.app.Activity
import android.content.Context
import dagger.Module
import dagger.Provides
import io.github.ldhnam.roomrxjava2.di.scope.ActivityScope


@Module
class ActivityModule(private val mActivity: Activity) {

    @Provides
    @ActivityScope
    fun provideActivity(): Activity = mActivity

    @Provides
    @ActivityScope
    fun provideContext(): Context = mActivity
}