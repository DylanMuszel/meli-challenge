package com.dylanmuszel.melichallenge.presentation.productlist

import com.dylanmuszel.core.fp.Failure
import com.dylanmuszel.core.fp.NetworkConnectionFailure
import com.dylanmuszel.core.fp.ServerFailure
import com.dylanmuszel.domain.Product
import com.dylanmuszel.melichallenge.framework.core.Logger
import com.dylanmuszel.melichallenge.presentation.core.BasePresenter
import com.dylanmuszel.melichallenge.presentation.model.ProductUI
import com.dylanmuszel.melichallenge.presentation.model.mapper.toProductUI
import com.dylanmuszel.usecases.product.SearchProductsUseCase
import javax.inject.Inject
import kotlinx.coroutines.launch

class ProductListPresenter @Inject constructor(
    private val logger: Logger,
    private val searchProductsUseCase: SearchProductsUseCase
) : BasePresenter<ProductListView>() {

    /** Invoked on view init given a [query] to search. */
    fun onInit(query: String) = launch {
        view?.toggleLoadingVisibility(true)
        view?.setSearchingQueryTitle(query)
        searchProductsUseCase(query).fold(
            { handleSearchError(query, it) },
            { handleSearchSuccess(it) })
        view?.toggleLoadingVisibility(false)
    }

    /** Invoked when a [product] is clicked. */
    fun onProductClicked(product: ProductUI) = view?.goToProductDetail(product)

    /** Invoked when the toolbar search button is clicked. */
    fun onSearchButtonClicked() = view?.goToSearch()

    private fun handleSearchError(query: String, failure: Failure) {
        when (failure) {
            NetworkConnectionFailure -> view?.showNetworkConnectionError()
            is ServerFailure -> {
                logger.e(TAG, "Error while searching $query", failure.exception)
                view?.showUnexpectedError()
            }
        }
    }

    private fun handleSearchSuccess(products: List<Product>) = view?.run {
        if (products.isNotEmpty()) {
            showProducts(products.map { it.toProductUI() })
        } else {
            showNoProductsError()
        }
    }

    companion object {
        private const val TAG = "ProductListPresenter"
    }
}
