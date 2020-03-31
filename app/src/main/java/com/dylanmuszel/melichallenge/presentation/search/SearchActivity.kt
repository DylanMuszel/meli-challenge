package com.dylanmuszel.melichallenge.presentation.search

import android.content.Context
import android.content.Intent
import com.dylanmuszel.melichallenge.presentation.core.BaseActivity

class SearchActivity : BaseActivity(SearchFragment.newInstance()) {

    /** Returns an intent to start [SearchActivity]. */
    fun getStarterIntent(context: Context) = Intent(context, SearchActivity::class.java)
}