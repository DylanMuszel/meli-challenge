package com.dylanmuszel.usecases.product

import arrow.core.Either
import arrow.core.orNull
import com.dylanmuszel.core.fp.FeatureFailure
import com.dylanmuszel.data.ProductRepository
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

class SearchProductsUseCaseTest {

    private lateinit var productRepository: ProductRepository
    private lateinit var useCase: SearchProductsUseCase

    @Before
    fun setup() {
        productRepository = mock()
        useCase = SearchProductsUseCase(productRepository)
    }

    @Test
    fun `given a list of products when use case is invoked then returns the list of products`() = runBlocking {

        // GIVEN
        val products = listOf<Product>(mock(), mock())
        whenever(productRepository.search(any())).thenReturn(Either.right(products))

        // WHEN
        val response = useCase("query")

        // THEN
        assertThat(response.isRight(), `is`(true))
        assertThat(response.orNull(), `is`(products))
    }

    @Test
    fun `given a failure when use case is invoked then returns the failure`() = runBlocking {

        // GIVEN
        val failure = object : FeatureFailure() {}
        whenever(productRepository.search(any())).thenReturn(Either.left(failure))

        // WHEN
        val response = useCase("query")

        // THEN
        assertThat(response.isLeft(), `is`(true))
        response.fold({ assertThat(it, instanceOf(FeatureFailure::class.java)) }, {})
    }
}
