package com.dylanmuszel.melichallenge.presentation.productlist

import com.dylanmuszel.domain.Product

interface ProductListView {

    /** Toggle loading indicator visibility. */
    fun toggleLoadingVisibility(isVisible: Boolean)

    /** Set the searching [query] title */
    fun setSearchingQueryTitle(query: String)

    /** Show an error message because the device hasn't internet connection. */
    fun showNetworkConnectionError()

    /** Show an error message because there was an unexpected error. */
    fun showUnexpectedError()

    /** Show an error message because the search doesn't retrieve any results. */
    fun showNoProductsError()

    /** Show the [products] retrieved. */
    fun showProducts(products: List<Product>)

    /** Open the search view to start a new one. */
    fun goToSearch()
}
