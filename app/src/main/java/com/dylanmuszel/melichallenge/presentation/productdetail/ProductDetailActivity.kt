package com.dylanmuszel.melichallenge.presentation.productdetail

import android.content.Context
import android.content.Intent
import com.dylanmuszel.melichallenge.presentation.core.BaseActivity
import com.dylanmuszel.melichallenge.presentation.model.ProductUI

class ProductDetailActivity : BaseActivity() {

    override val fragment get() = ProductDetailFragment.newInstance(requireExtra(PRODUCT_EXTRA))

    companion object {

        private const val PRODUCT_EXTRA = "PRODUCT_EXTRA"

        /** Returns an intent to start [ProductDetailActivity] showing the given [product]. */
        fun getStarterIntent(context: Context, product: ProductUI) =
            Intent(context, ProductDetailActivity::class.java).apply {
                putExtra(PRODUCT_EXTRA, product)
            }
    }
}
