package com.dylanmuszel.melichallenge.presentation.search

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dylanmuszel.domain.Product
import com.dylanmuszel.melichallenge.R
import com.dylanmuszel.melichallenge.databinding.FragmentSearchBinding
import com.dylanmuszel.melichallenge.presentation.core.BaseFragment
import com.google.android.material.snackbar.Snackbar

class SearchFragment private constructor(): BaseFragment<FragmentSearchBinding, SearchPresenter>(), SearchView {

    override val inflate: (inflater: LayoutInflater, container: ViewGroup?, attachToRoot: Boolean) -> FragmentSearchBinding =
        FragmentSearchBinding::inflate

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Log.d("DylanLog", "on view created")
        binding.searchButton.setOnClickListener {
            presenter.onSearchButtonClicked(binding.searchInput.text.toString())
        }
    }

    override fun showSearchEmptyError() {
        binding.searchInput.error = getString(R.string.search_empty_error)
    }

    override fun showNetworkConnection() {
        Snackbar.make(binding.searchButton, R.string.network_error, Snackbar.LENGTH_SHORT).show()
    }

    override fun showUnexpectedError() {
        Snackbar.make(binding.searchButton, R.string.unexpected_error, Snackbar.LENGTH_SHORT).show()
    }

    override fun goToProductList(products: List<Product>) {
    }

    companion object {

        /** Returns a new instance of the [SearchFragment]. */
        fun newInstance() = SearchFragment()
    }
}