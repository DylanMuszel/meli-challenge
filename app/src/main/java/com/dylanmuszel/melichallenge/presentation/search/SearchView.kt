package com.dylanmuszel.melichallenge.presentation.search

interface SearchView {

    /** Show an error message because the search query was empty. */
    fun showSearchEmptyError()

    /** Open the product list view with the given [query] to be searched. */
    fun goToProductList(query: String)
}
