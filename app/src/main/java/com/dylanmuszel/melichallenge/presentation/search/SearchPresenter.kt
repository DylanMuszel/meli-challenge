package com.dylanmuszel.melichallenge.presentation.search

import com.dylanmuszel.melichallenge.presentation.core.BasePresenter
import javax.inject.Inject

class SearchPresenter @Inject constructor() : BasePresenter<SearchView>() {

    /** Invoked when the search button is clicked. */
    fun onSearchButtonClicked(query: String) {
        if (query.isNotBlank()) {
            view?.goToProductList(query)
        } else {
            view?.showSearchEmptyError()
        }
    }
}
