package com.dylanmuszel.melichallenge

import com.dylanmuszel.melichallenge.di.DaggerAppComponent
import com.facebook.drawee.backends.pipeline.Fresco
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class MeliChallengeApp : DaggerApplication() {

    override fun onCreate() {
        super.onCreate()
        Fresco.initialize(applicationContext)
    }

    override fun applicationInjector(): AndroidInjector<MeliChallengeApp> = DaggerAppComponent
        .builder()
        .application(this)
        .sharedPreferencesName(SHARED_PREFERENCES_NAME)
        .create(this)

    companion object {

        private const val SHARED_PREFERENCES_NAME = "MELI_CHALLENGE_SHARED_PREFERENCES"
    }
}