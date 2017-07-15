package io.github.ldhnam.roomrxjava2

import android.app.Application
import android.content.Context
import com.facebook.drawee.backends.pipeline.Fresco
import io.github.ldhnam.roomrxjava2.di.component.AppComponent
import io.github.ldhnam.roomrxjava2.di.component.DaggerAppComponent
import io.github.ldhnam.roomrxjava2.di.module.AppModule


class App: Application() {

    var mAppComponent: AppComponent? = null

    override fun onCreate() {
        super.onCreate()
        Fresco.initialize(this)
    }

    var component: AppComponent
        get() {
            if (mAppComponent == null) {
                mAppComponent = DaggerAppComponent.builder()
                        .appModule(AppModule(this))
                        .build()
            }
            return mAppComponent as AppComponent
        }
        set(applicationComponent) {
            mAppComponent = applicationComponent
        }

    companion object {

        operator fun get(context: Context): App {
            return context.applicationContext as App
        }
    }
}