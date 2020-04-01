package com.dylanmuszel.melichallenge.presentation.productdetail

import com.dylanmuszel.melichallenge.presentation.core.BasePresenter
import com.dylanmuszel.melichallenge.presentation.model.ProductUI
import javax.inject.Inject

class ProductDetailPresenter @Inject constructor() : BasePresenter<ProductDetailView>() {

    lateinit var product: ProductUI

    /** Invoked on view init given a [product] to show. */
    fun onInit(product: ProductUI) {
        this.product = product
        view?.showProduct(product)
    }

    /** Invoked when the toolbar search button is clicked. */
    fun onSearchButtonClicked() = view?.goToSearch()

    fun onTitleClicked() = view?.openWeb(product.permalink)
}
