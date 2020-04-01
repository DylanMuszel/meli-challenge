package com.dylanmuszel.melichallenge.presentation.productdetail

import com.dylanmuszel.melichallenge.presentation.core.BasePresenter
import com.dylanmuszel.melichallenge.presentation.model.ProductUI
import javax.inject.Inject

class ProductDetailPresenter @Inject constructor() : BasePresenter<ProductDetailView>() {

    /** Invoked on view init given a [product] to show. */
    fun onInit(product: ProductUI) = view?.showProduct(product)

    /** Invoked when the toolbar search button is clicked. */
    fun onSearchButtonClicked() = view?.goToSearch()

}