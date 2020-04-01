package com.dylanmuszel.melichallenge.presentation.productlist

import android.content.Context
import android.content.Intent
import com.dylanmuszel.melichallenge.presentation.core.BaseActivity

class ProductListActivity : BaseActivity() {

    override val fragment get() = ProductListFragment.newInstance(requireExtra(QUERY_EXTRA))

    companion object {

        private const val QUERY_EXTRA = "QUERY_EXTRA"

        /** Returns an intent to start [ProductListActivity] searching by the given [query]. */
        fun getStarterIntent(context: Context, query: String) = Intent(context, ProductListActivity::class.java).apply {
            putExtra(QUERY_EXTRA, query)
        }
    }
}
