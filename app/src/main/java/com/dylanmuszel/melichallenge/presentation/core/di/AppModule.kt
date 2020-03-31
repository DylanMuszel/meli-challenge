package com.dylanmuszel.melichallenge.presentation.core.di

import android.app.Application
import android.content.Context
import com.dylanmuszel.melichallenge.framework.product.ProductModule
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton

@Module(
    includes = [
        ProductModule::class
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