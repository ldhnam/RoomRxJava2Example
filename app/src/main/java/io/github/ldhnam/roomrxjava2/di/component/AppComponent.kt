package io.github.ldhnam.roomrxjava2.di.component

import dagger.Component
import io.github.ldhnam.roomrxjava2.data.local.AppDatabase
import io.github.ldhnam.roomrxjava2.data.remote.RestService
import io.github.ldhnam.roomrxjava2.di.module.AppModule
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(AppModule::class))
interface AppComponent {

    fun restService(): RestService

    fun appDatabase(): AppDatabase

}