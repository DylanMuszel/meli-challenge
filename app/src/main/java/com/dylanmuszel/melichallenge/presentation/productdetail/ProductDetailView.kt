package com.dylanmuszel.melichallenge.presentation.productdetail

import com.dylanmuszel.melichallenge.presentation.model.ProductUI

interface ProductDetailView {

    /** Show the [product] detail. */
    fun showProduct(product: ProductUI)

    /** Open the search view to start a new one. */
    fun goToSearch()
}
