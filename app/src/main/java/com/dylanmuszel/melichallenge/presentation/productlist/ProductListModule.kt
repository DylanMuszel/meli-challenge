package com.dylanmuszel.melichallenge.presentation.productlist

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ProductListModule {

    @ContributesAndroidInjector
    abstract fun provideProductListActivity(): ProductListActivity

    @ContributesAndroidInjector
    abstract fun provideProductListFragment(): ProductListFragment
}
