package com.dylanmuszel.melichallenge

import com.dylanmuszel.melichallenge.presentation.core.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class MeliChallengeApp : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<MeliChallengeApp> = DaggerAppComponent
        .builder()
        .application(this)
        .create(this)
}