package com.dylanmuszel.melichallenge.presentation.search

import com.dylanmuszel.domain.Product

interface SearchView {

    fun showSearchEmptyError()

    fun goToProductList(products: List<Product>)

    fun showNetworkConnection()

    fun showUnexpectedError()
}
