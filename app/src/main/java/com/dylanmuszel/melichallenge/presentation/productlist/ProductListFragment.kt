package com.dylanmuszel.melichallenge.presentation.productlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.dylanmuszel.domain.Product
import com.dylanmuszel.melichallenge.R
import com.dylanmuszel.melichallenge.databinding.FragmentProductListBinding
import com.dylanmuszel.melichallenge.presentation.core.BaseFragment
import com.dylanmuszel.melichallenge.presentation.search.SearchActivity

class ProductListFragment : BaseFragment<FragmentProductListBinding, ProductListPresenter>(), ProductListView {

    private val productListAdapter = ProductListAdapter()

    override val inflate: (inflater: LayoutInflater, container: ViewGroup?, attachToRoot: Boolean) -> FragmentProductListBinding =
        FragmentProductListBinding::inflate

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        with(binding.productListRecycler) {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = productListAdapter
        }
        binding.toolbar.onSearchClicked = { presenter.onSearchButtonClicked() }
        presenter.onInit(requireArgument(QUERY_EXTRA))
    }

    override fun toggleLoadingVisibility(isVisible: Boolean) {
        binding.loading.isVisible = isVisible
    }

    override fun setSearchingQueryTitle(query: String) {
        binding.toolbar.setTitle(R.string.product_list_title, query)
    }

    override fun showNetworkConnectionError() = showError(R.drawable.ic_router, R.string.network_error)

    override fun showUnexpectedError() = showError(R.drawable.ic_dissatisfied, R.string.unexpected_error)

    override fun showNoProductsError() = showError(R.drawable.ic_search, R.string.no_products_found)

    override fun showProducts(products: List<Product>) = productListAdapter.setProducts(products)

    override fun goToSearch() = startActivity(SearchActivity.getStarterIntent(requireContext()))

    private fun showError(@DrawableRes imageRes: Int, @StringRes textRes: Int) = with(binding) {
        errorImage.setImageResource(imageRes)
        errorText.setText(textRes)
        error.isVisible = true
    }

    companion object {

        private const val QUERY_EXTRA = "QUERY_EXTRA"

        /** Returns a new instance of the [ProductListFragment]. */
        fun newInstance(query: String) = ProductListFragment().apply {
            arguments = bundleOf(QUERY_EXTRA to query)
        }
    }
}