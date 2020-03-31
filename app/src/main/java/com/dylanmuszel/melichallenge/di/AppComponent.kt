package com.dylanmuszel.melichallenge.di

import android.app.Application
import com.dylanmuszel.melichallenge.MeliChallengeApp
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        AppModule::class
    ]
)
@Singleton
interface AppComponent : AndroidInjector<MeliChallengeApp> {

    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<MeliChallengeApp>() {

        @BindsInstance
        abstract fun application(application: Application): Builder

        @BindsInstance
        abstract fun sharedPreferencesName(sharedPrefName: String): Builder
    }
}
