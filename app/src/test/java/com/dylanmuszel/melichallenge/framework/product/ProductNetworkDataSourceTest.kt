package com.dylanmuszel.melichallenge.framework.product

import arrow.core.orNull
import com.dylanmuszel.core.fp.NetworkConnectionFailure
import com.dylanmuszel.core.fp.ServerFailure
import com.dylanmuszel.domain.Product
import com.dylanmuszel.melichallenge.framework.core.network.ConnectivityInfo
import com.dylanmuszel.melichallenge.framework.core.network.NetworkResponse
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.instanceOf
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test
import retrofit2.HttpException

class ProductNetworkDataSourceTest {

    private lateinit var connectivityInfo: ConnectivityInfo
    private lateinit var productService: ProductService
    private lateinit var productNetworkDataSource: ProductNetworkDataSource

    @Before
    fun setup() {
        connectivityInfo = mock()
        productService = mock()
        productNetworkDataSource = ProductNetworkDataSource(connectivityInfo, productService)
    }

    @Test
    fun `given offline connectivity when searching then returns a network failure`() = runBlocking {

        // GIVEN
        whenever(connectivityInfo.isOnline).thenReturn(false)

        // WHEN
        val result = productNetworkDataSource.search("query")

        // THEN
        assertThat(result.isLeft(), `is`(true))
        result.fold({ assertThat(it, instanceOf(NetworkConnectionFailure::class.java)) }, {})
    }

    @Test
    fun `given online connectivity but http exception when searching then returns a server failure`() = runBlocking {

        // GIVEN
        val exception = mock<HttpException>()
        whenever(connectivityInfo.isOnline).thenReturn(true)
        whenever(productService.search(any())).thenThrow(exception)

        // WHEN
        val result = productNetworkDataSource.search("query")

        // THEN
        assertThat(result.isLeft(), `is`(true))
        result.fold({
            assertThat(it, instanceOf(ServerFailure::class.java))
            assertThat((it as ServerFailure).exception, instanceOf(HttpException::class.java))
        }, {})
    }

    @Test
    fun `given a list of products when searching then returns the product list`() = runBlocking {

        // GIVEN
        val list = listOf<Product>(mock(), mock())
        whenever(connectivityInfo.isOnline).thenReturn(true)
        whenever(productService.search(any())).thenReturn(NetworkResponse(list))

        // WHEN
        val result = productNetworkDataSource.search("query")

        // THEN
        assertThat(result.isRight(), `is`(true))
        assertThat(result.orNull(), `is`(list))
    }
}
