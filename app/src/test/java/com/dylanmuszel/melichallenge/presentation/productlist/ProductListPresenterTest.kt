package com.dylanmuszel.melichallenge.presentation.productlist

import arrow.core.Either
import com.dylanmuszel.core.fp.NetworkConnectionFailure
import com.dylanmuszel.core.fp.ServerFailure
import com.dylanmuszel.domain.Product
import com.dylanmuszel.domain.ProductAddress
import com.dylanmuszel.domain.ProductAttribute
import com.dylanmuszel.domain.ProductAttributeValue
import com.dylanmuszel.domain.Seller
import com.dylanmuszel.melichallenge.CoroutineTestRule
import com.dylanmuszel.melichallenge.presentation.model.mapper.toProductUI
import com.dylanmuszel.usecases.product.SearchProductsUseCase
import com.nhaarman.mockitokotlin2.eq
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.anyString
import org.mockito.Mockito.inOrder

class ProductListPresenterTest {

    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    private lateinit var searchProductsUseCase: SearchProductsUseCase
    private lateinit var view: ProductListView
    private lateinit var presenter: ProductListPresenter

    @Before
    fun setup() {
        searchProductsUseCase = mock()
        view = mock()
        presenter = ProductListPresenter(searchProductsUseCase).apply {
            onCreate(view)
        }
    }

    @Test
    fun `given a successful query when init then set title and products`() = runBlocking {

        // GIVEN
        val query = "query"
        val products = listOf(mockProduct(), mockProduct(), mockProduct())
        whenever(searchProductsUseCase(eq(query))).thenReturn(Either.right(products))

        // WHEN
        presenter.onInit(query).join()

        // THEN
        with(inOrder(view)) {
            verify(view).toggleLoadingVisibility(eq(true))
            verify(view).setSearchingQueryTitle(eq(query))
            verify(view).showProducts(eq(products.map { it.toProductUI() }))
            verify(view).toggleLoadingVisibility(false)
        }
    }

    @Test
    fun `given a empty query response when init then set title and error`() = runBlocking {

        // GIVEN
        val query = "query"
        val products = listOf<Product>()
        whenever(searchProductsUseCase(eq(query))).thenReturn(Either.right(products))

        // WHEN
        presenter.onInit(query).join()

        // THEN
        with(inOrder(view)) {
            verify(view).toggleLoadingVisibility(eq(true))
            verify(view).setSearchingQueryTitle(eq(query))
            verify(view).showNoProductsError()
            verify(view).toggleLoadingVisibility(false)
        }
    }

    @Test
    fun `given a network failure when init then set title and error`() = runBlocking {

        // GIVEN
        val query = "query"
        whenever(searchProductsUseCase(eq(query))).thenReturn(Either.left(NetworkConnectionFailure))

        // WHEN
        presenter.onInit(query).join()

        // THEN
        with(inOrder(view)) {
            verify(view).toggleLoadingVisibility(eq(true))
            verify(view).setSearchingQueryTitle(eq(query))
            verify(view).showNetworkConnectionError()
            verify(view).toggleLoadingVisibility(false)
        }
    }

    @Test
    fun `given a server failure when init then set title and error`() = runBlocking {

        // GIVEN
        val query = "query"
        val customException = object : Exception() {}
        whenever(searchProductsUseCase(eq(query))).thenReturn(Either.left(ServerFailure(customException)))

        // WHEN
        presenter.onInit(query).join()

        // THEN
        with(inOrder(view)) {
            verify(view).toggleLoadingVisibility(eq(true))
            verify(view).setSearchingQueryTitle(eq(query))
            verify(view).showUnexpectedError()
            verify(view).toggleLoadingVisibility(false)
        }
    }

    private fun mockProduct() = Product(
        title = "title",
        price = 1200f,
        availableQuantity = 3,
        soldQuantity = 1,
        seller = Seller("gold"),
        condition = "new",
        permalink = "",
        thumbnail = "",
        address = ProductAddress("Palermo", "Capital Federal"),
        attributes = listOf(ProductAttribute("", listOf(ProductAttributeValue(""), ProductAttributeValue(""))))
    )
}
