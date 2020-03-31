package com.dylanmuszel.melichallenge.presentation.search

import com.dylanmuszel.domain.Product

interface SearchView {

    /** Show an error message because the search query was empty. */
    fun showSearchEmptyError()

    /** Show an error message because there was no connection. */
    fun showNetworkConnection()

    /** Show an error message because there was an unexpected error. */
    fun showUnexpectedError()

    /** Open the product list view with the given [products]. */
    fun goToProductList(products: List<Product>)
}
