package com.dylanmuszel.melichallenge.presentation.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dylanmuszel.melichallenge.R
import com.dylanmuszel.melichallenge.databinding.FragmentSearchBinding
import com.dylanmuszel.melichallenge.presentation.core.BaseFragment
import com.dylanmuszel.melichallenge.presentation.productlist.ProductListActivity

class SearchFragment : BaseFragment<FragmentSearchBinding, SearchPresenter>(), SearchView {

    override val inflate: (inflater: LayoutInflater, container: ViewGroup?, attachToRoot: Boolean) -> FragmentSearchBinding =
        FragmentSearchBinding::inflate

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.searchButton.setOnClickListener {
            presenter.onSearchButtonClicked(binding.searchInput.text.toString())
        }
    }

    override fun showSearchEmptyError() {
        binding.searchInput.error = getString(R.string.search_empty_error)
    }

    override fun goToProductList(query: String) = startActivity(ProductListActivity.getStarterIntent(requireContext(), query))

    companion object {

        /** Returns a new instance of the [SearchFragment]. */
        fun newInstance() = SearchFragment()
    }
}