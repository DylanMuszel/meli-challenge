package com.dylanmuszel.melichallenge.presentation.productdetail

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ProductDetailModule {

    @ContributesAndroidInjector
    abstract fun provideProductDetailActivity(): ProductDetailActivity

    @ContributesAndroidInjector
    abstract fun provideProductDetailFragment(): ProductDetailFragment
}
