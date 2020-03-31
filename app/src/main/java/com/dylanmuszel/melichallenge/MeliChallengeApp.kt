package com.dylanmuszel.melichallenge

import com.dylanmuszel.melichallenge.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class MeliChallengeApp : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<MeliChallengeApp> = DaggerAppComponent
        .builder()
        .application(this)
        .sharedPreferencesName(SHARED_PREFERENCES_NAME)
        .create(this)

    companion object {

        private const val SHARED_PREFERENCES_NAME = "MELI_CHALLENGE_SHARED_PREFERENCES"
    }
}