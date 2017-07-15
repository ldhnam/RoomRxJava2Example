package io.github.ldhnam.roomrxjava2.di.component

import dagger.Component
import io.github.ldhnam.roomrxjava2.di.module.ActivityModule
import io.github.ldhnam.roomrxjava2.di.scope.ConfigPersistent


@ConfigPersistent
@Component(dependencies = arrayOf(AppComponent::class))
interface ConfigPersistentComponent {
    fun activityComponent(activityModule: ActivityModule): ActivityComponent

}