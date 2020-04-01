package com.dylanmuszel.melichallenge.presentation.productlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.annotation.VisibleForTesting
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DividerItemDecoration
import com.dylanmuszel.melichallenge.R
import com.dylanmuszel.melichallenge.databinding.FragmentProductListBinding
import com.dylanmuszel.melichallenge.presentation.core.BaseFragment
import com.dylanmuszel.melichallenge.presentation.core.getSerializableList
import com.dylanmuszel.melichallenge.presentation.model.ProductUI
import com.dylanmuszel.melichallenge.presentation.productdetail.ProductDetailActivity
import com.dylanmuszel.melichallenge.presentation.search.SearchActivity

class ProductListFragment : BaseFragment<FragmentProductListBinding, ProductListPresenter>(), ProductListView {

    private val list = mutableListOf<ProductUI>()
    private lateinit var productListAdapter: ProductListAdapter

    override val inflate: (
        inflater: LayoutInflater,
        container: ViewGroup?,
        attachToRoot: Boolean
    ) -> FragmentProductListBinding = FragmentProductListBinding::inflate

    override fun init() {
        presenter.onInit(requireArgument(QUERY_EXTRA))
    }

    override fun setUI() {
        productListAdapter = ProductListAdapter(list) { presenter.onProductClicked(it) }
        with(binding.productListRecycler) {
            adapter = productListAdapter
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        }
        binding.toolbar.onSearchClicked = { presenter.onSearchButtonClicked() }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putSerializable(PRODUCT_LIST_EXTRA, ArrayList(list))
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        list.clear()
        savedInstanceState?.getSerializableList<ProductUI>(PRODUCT_LIST_EXTRA)?.let { list.addAll(it) }
        super.onViewCreated(view, savedInstanceState)
    }

    override fun toggleLoadingVisibility(isVisible: Boolean) {
        binding.loading.isVisible = isVisible
    }

    override fun setSearchingQueryTitle(query: String) {
        binding.toolbar.setTitle(R.string.product_list_title, query)
    }

    override fun showNetworkConnectionError() = showError(R.drawable.ic_router, R.string.network_error)

    override fun showUnexpectedError() = showError(R.drawable.ic_dissatisfied, R.string.unexpected_error)

    override fun showNoProductsError() = showError(R.drawable.ic_search, R.string.product_list_empty_error)

    override fun showProducts(products: List<ProductUI>) {
        productListAdapter.setProducts(products)
        binding.productListRecycler.isVisible = true
        binding.error.isVisible = false
    }

    override fun goToSearch() = startActivity(SearchActivity.getStarterIntent(requireContext()))

    override fun goToProductDetail(product: ProductUI) =
        startActivity(ProductDetailActivity.getStarterIntent(requireContext(), product))

    private fun showError(@DrawableRes imageRes: Int, @StringRes textRes: Int) = with(binding) {
        errorImage.setImageResource(imageRes)
        errorText.setText(textRes)
        productListRecycler.isVisible = false
        error.isVisible = true
    }

    companion object {

        private const val PRODUCT_LIST_EXTRA = "PRODUCT_LIST_EXTRA"

        @VisibleForTesting
        const val QUERY_EXTRA = "QUERY_EXTRA"

        /** Returns a new instance of the [ProductListFragment]. */
        fun newInstance(query: String) = ProductListFragment().apply {
            arguments = bundleOf(QUERY_EXTRA to query)
        }
    }
}
