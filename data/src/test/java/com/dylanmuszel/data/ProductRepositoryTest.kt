package com.dylanmuszel.data

import arrow.core.Either
import arrow.core.orNull
import com.dylanmuszel.core.fp.FeatureFailure
import com.dylanmuszel.domain.Product
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.instanceOf
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test

class ProductRepositoryTest {

    private lateinit var productDataSource: ProductDataSource
    private lateinit var productRepository: ProductRepository

    @Before
    fun setup() {
        productDataSource = mock()
        productRepository = ProductRepository(productDataSource)
    }

    @Test
    fun `given a list of products when searching then returns the list of products`() = runBlocking {

        // GIVEN
        val products = listOf<Product>(mock(), mock())
        whenever(productDataSource.search(any())).thenReturn(Either.right(products))

        // WHEN
        val response = productRepository.search("query")

        // THEN
        assertThat(response.isRight(), `is`(true))
        assertThat(response.orNull(), `is`(products))
    }

    @Test
    fun `given a failure when searching then returns the failure`() = runBlocking {

        // GIVEN
        val failure = object : FeatureFailure() {}
        whenever(productDataSource.search(any())).thenReturn(Either.left(failure))

        // WHEN
        val response = productRepository.search("query")

        // THEN
        assertThat(response.isLeft(), `is`(true))
        response.fold({ assertThat(it, instanceOf(FeatureFailure::class.java)) }, {})
    }
}

