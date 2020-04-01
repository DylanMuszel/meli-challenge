package com.dylanmuszel.melichallenge.presentation.core

import android.os.Bundle
import androidx.annotation.CallSuper
import androidx.fragment.app.commit
import com.dylanmuszel.melichallenge.R
import dagger.android.support.DaggerAppCompatActivity

/**
 * Base [DaggerAppCompatActivity] implementation used to handle the base usage of a 1 activity - 1 fragment pattern.
 */
abstract class BaseActivity : DaggerAppCompatActivity() {

    /** The fragment that will be inserted on the activity. */
    abstract val fragment: BaseFragment<*, *>

    /** On the Activity create the fragment is inserted. */
    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)

        if (savedInstanceState == null) {
            supportFragmentManager.commit { replace(R.id.base_content, fragment) }
        }
    }

    /** Getting an extra from the intent asserting it's instantiated and it's not null. **/
    @Suppress("UNCHECKED_CAST")
    protected fun <T> requireExtra(key: String): T = intent.extras!!.get(key) as T
}
