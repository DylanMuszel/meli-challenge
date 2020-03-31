package com.dylanmuszel.melichallenge.presentation.productlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import com.dylanmuszel.melichallenge.databinding.FragmentProductListBinding
import com.dylanmuszel.melichallenge.presentation.core.BaseFragment
import com.dylanmuszel.melichallenge.presentation.search.SearchActivity

class ProductListFragment : BaseFragment<FragmentProductListBinding, ProductListPresenter>(), ProductListView {

    override val inflate: (inflater: LayoutInflater, container: ViewGroup?, attachToRoot: Boolean) -> FragmentProductListBinding =
        FragmentProductListBinding::inflate

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.toolbar.onSearchClicked = { presenter.onSearchButtonClicked() }
    }

    override fun goToSearch() = startActivity(SearchActivity.getStarterIntent(requireContext()))

    companion object {

        private const val QUERY_EXTRA = "QUERY_EXTRA"

        /** Returns a new instance of the [ProductListFragment]. */
        fun newInstance(query: String) = ProductListFragment().apply {
            arguments = bundleOf(QUERY_EXTRA to query)
        }
    }
}