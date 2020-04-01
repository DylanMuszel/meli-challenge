package com.dylanmuszel.melichallenge.di

import android.app.Application
import android.content.Context
import com.dylanmuszel.melichallenge.framework.product.ProductModule
import com.dylanmuszel.melichallenge.presentation.productdetail.ProductDetailModule
import com.dylanmuszel.melichallenge.presentation.productlist.ProductListModule
import com.dylanmuszel.melichallenge.presentation.search.SearchModule
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton

@Module(
    includes = [
        ProductModule::class,
        SearchModule::class,
        ProductListModule::class,
        ProductDetailModule::class
    ]
)
class AppModule {

    @Provides
    @Singleton
    @Named("applicationContext")
    fun provideApplicationContext(application: Application): Context = application.applicationContext

    @Provides
    @Singleton
    @Named("baseContext")
    fun provideBaseContext(application: Application): Context = application.baseContext
}