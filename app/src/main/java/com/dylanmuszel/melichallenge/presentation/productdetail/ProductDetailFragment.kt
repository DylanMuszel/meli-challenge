package com.dylanmuszel.melichallenge.presentation.productdetail

import android.content.Intent
import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.VisibleForTesting
import androidx.core.content.ContextCompat
import androidx.core.net.toUri
import androidx.core.os.bundleOf
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import com.dylanmuszel.melichallenge.databinding.FragmentProductDetailBinding
import com.dylanmuszel.melichallenge.presentation.core.BaseFragment
import com.dylanmuszel.melichallenge.presentation.model.ProductUI
import com.dylanmuszel.melichallenge.presentation.search.SearchActivity

class ProductDetailFragment : BaseFragment<FragmentProductDetailBinding, ProductDetailPresenter>(), ProductDetailView {

    override val inflate: (
        inflater: LayoutInflater,
        container: ViewGroup?,
        attachToRoot: Boolean
    ) -> FragmentProductDetailBinding = FragmentProductDetailBinding::inflate

    override fun setUI() {
        presenter.onInit(requireArgument(PRODUCT_EXTRA))
        binding.titleText.setOnClickListener { presenter.onTitleClicked() }
        binding.toolbar.onSearchClicked = { presenter.onSearchButtonClicked() }
    }

    override fun showProduct(product: ProductUI) = with(binding) {
        with(product) {
            imageView.setImageURI(thumbnail)
            conditionText.isInvisible = conditionRes == null
            conditionRes?.let { conditionText.text = getString(it) }
            titleText.text = title
            priceText.text = price
            availableQuantityText.text = getString(availableQuantityTextRes, availableQuantity)
            soldQuantityText.text = getString(soldQuantityTextRes, soldQuantity)
            sellerLocationText.text = address
            sellerStatusGroup.isVisible = sellerStatus != null
            sellerStatus?.let {
                sellerStatusText.text = getString(it.textRes)
                sellerStatusText.setTextColor(ContextCompat.getColor(requireContext(), it.colorRes))
                sellerStatusIcon.imageTintList =
                    ColorStateList.valueOf(ContextCompat.getColor(requireContext(), it.colorRes))
            }
            attributesGrid.adapter = ProductAttributeAdapter(attributes)
            attributesGroup.isVisible = attributes.isNotEmpty()
        }
    }

    override fun goToSearch() = startActivity(SearchActivity.getStarterIntent(requireContext()))

    override fun openWeb(link: String) = startActivity(Intent(Intent.ACTION_VIEW, link.toUri()))

    companion object {

        @VisibleForTesting
        const val PRODUCT_EXTRA = "PRODUCT_EXTRA"

        /** Returns a new instance of the [ProductDetailFragment]. */
        fun newInstance(product: ProductUI) = ProductDetailFragment().apply {
            arguments = bundleOf(PRODUCT_EXTRA to product)
        }
    }
}
