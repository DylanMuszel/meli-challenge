package com.dylanmuszel.melichallenge.presentation.productdetail

import com.dylanmuszel.melichallenge.presentation.model.ProductUI
import com.nhaarman.mockitokotlin2.eq
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class ProductDetailPresenterTest {

    private lateinit var view: ProductDetailView
    private lateinit var presenter: ProductDetailPresenter

    @Before
    fun setup() {
        view = mock()
        presenter = ProductDetailPresenter().apply {
            onCreate(view)
        }
    }

    @Test
    fun `given a product when init then show the product`() {

        // GIVEN
        val product = mock<ProductUI>()

        // WHEN
        presenter.onInit(product)

        // THEN
        verify(view).showProduct(eq(product))
    }

    @Test
    fun `when search button is clicked then go to search`() = runBlocking {

        // GIVEN

        // WHEN
        presenter.onSearchButtonClicked()

        // THEN
        verify(view).goToSearch()
    }
}
