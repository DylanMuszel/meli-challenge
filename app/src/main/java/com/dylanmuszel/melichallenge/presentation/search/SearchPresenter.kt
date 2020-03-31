package com.dylanmuszel.melichallenge.presentation.search

import android.util.Log
import com.dylanmuszel.core.fp.Failure
import com.dylanmuszel.core.fp.NetworkConnection
import com.dylanmuszel.core.fp.ServerError
import com.dylanmuszel.domain.Product
import com.dylanmuszel.melichallenge.presentation.core.BasePresenter
import com.dylanmuszel.usecases.product.EmptySearch
import com.dylanmuszel.usecases.product.SearchProductsUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class SearchPresenter @Inject constructor(private val searchProductsUseCase: SearchProductsUseCase) :
    BasePresenter<SearchView>() {

    fun onSearchButtonClicked(query: String) = launch {
        searchProductsUseCase(query).fold(::handleFailure, ::handleSuccess)
    }

    private fun handleFailure(failure: Failure) {
        when (failure) {
            EmptySearch -> view?.showSearchEmptyError()
            NetworkConnection -> view?.showNetworkConnection()
            is ServerError -> {
                Log.e(TAG, "Server error while searching products", failure.exception)
                view?.showUnexpectedError()
            }
        }
    }

    private fun handleSuccess(products: List<Product>) {
        view?.goToProductList(products)
    }

    companion object {
        private const val TAG = "SearchPresenter"
    }
}
