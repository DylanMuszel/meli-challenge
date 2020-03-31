package com.dylanmuszel.melichallenge.presentation.search

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class SearchModule {

    @ContributesAndroidInjector
    abstract fun provideSearchActivity(): SearchActivity

    @ContributesAndroidInjector
    abstract fun provideSearchFragment(): SearchFragment
}