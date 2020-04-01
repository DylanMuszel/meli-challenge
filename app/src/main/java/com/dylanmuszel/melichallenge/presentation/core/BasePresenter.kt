package com.dylanmuszel.melichallenge.presentation.core

import androidx.annotation.CallSuper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel

/**
 * Base presenter for MVP.
 * It's also a CoroutineScope that lives between the view onCreate and onDestroy.
 */
abstract class BasePresenter<T> : CoroutineScope by CoroutineScope(Dispatchers.Main) {

    /** View attached to the presenter. */
    protected var view: T? = null

    /** Invoked on [view] created. Attaching it. */
    @CallSuper
    fun onCreate(view: T) {
        this.view = view
        onCreate()
    }

    /** Invoked on [view] created. Override if necessary. */
    open fun onCreate() {}

    /**
     * Invoked on [view] destroy.
     * View is detached and all the jobs attached to the presenter scope are cancelled.
     *
     * Override if necessary.
     */
    @CallSuper
    open fun onDestroy() {
        cancel()
        view = null
    }
}
