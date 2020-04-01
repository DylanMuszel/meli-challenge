package com.dylanmuszel.melichallenge.presentation.productlist

import com.dylanmuszel.core.fp.Failure
import com.dylanmuszel.core.fp.NetworkConnectionFailure
import com.dylanmuszel.core.fp.ServerFailure
import com.dylanmuszel.domain.Product
import com.dylanmuszel.melichallenge.presentation.core.BasePresenter
import com.dylanmuszel.melichallenge.presentation.model.ProductUI
import com.dylanmuszel.melichallenge.presentation.model.mapper.toProductUI
import com.dylanmuszel.usecases.product.SearchProductsUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class ProductListPresenter @Inject constructor(
    private val searchProductsUseCase: SearchProductsUseCase
) : BasePresenter<ProductListView>() {

    /** Invoked on view init given a [query] to search. */
    fun onInit(query: String) = launch {
        view?.toggleLoadingVisibility(true)
        view?.setSearchingQueryTitle(query)
        searchProductsUseCase(query).fold(::handleSearchError, ::handleSearchSuccess)
        view?.toggleLoadingVisibility(false)
    }

    /** Invoked when a [product] is clicked. */
    fun onProductClicked(product: ProductUI) = view?.goToProductDetail(product)

    /** Invoked when the toolbar search button is clicked. */
    fun onSearchButtonClicked() = view?.goToSearch()

    private fun handleSearchError(failure: Failure) {
        when (failure) {
            NetworkConnectionFailure -> view?.showNetworkConnectionError()
            is ServerFailure -> view?.showUnexpectedError()
        }
    }

    private fun handleSearchSuccess(products: List<Product>) = view?.run {
        if (products.isNotEmpty()) {
            showProducts(products.map { it.toProductUI() })
        } else {
            showNoProductsError()
        }
    }
}
