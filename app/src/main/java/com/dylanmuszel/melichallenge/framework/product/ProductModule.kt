package com.dylanmuszel.melichallenge.framework.product

import com.dylanmuszel.melichallenge.framework.core.network.ApiInstance
import com.dylanmuszel.melichallenge.framework.core.network.ConnectivityInfo
import dagger.Module
import dagger.Provides

@Module
object ProductModule {

    @JvmStatic
    @Provides
    fun provideProductService(apiInstance: ApiInstance) = apiInstance.getService(ProductService::class.java)

    @JvmStatic
    @Provides
    fun provideDataSource(
        connectivityInfo: ConnectivityInfo,
        productService: ProductService
    ) =  ProductNetworkDataSource(connectivityInfo, productService)
}