package com.dylanmuszel.melichallenge.presentation.productlist

import com.dylanmuszel.melichallenge.presentation.core.BasePresenter
import javax.inject.Inject

class ProductListPresenter @Inject constructor() : BasePresenter<ProductListView>() {

    /** Invoked when the toolbar search button is clicked. */
    fun onSearchButtonClicked() = view?.goToSearch()
}
