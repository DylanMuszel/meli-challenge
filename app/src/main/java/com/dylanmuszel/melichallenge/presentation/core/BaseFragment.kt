package com.dylanmuszel.melichallenge.presentation.core

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.viewbinding.ViewBinding
import dagger.android.support.DaggerFragment
import javax.inject.Inject

/**
 * Base [DaggerFragment] implementation for MVP.
 * It takes a [ViewBinding] [T] that will inflate a layout and bind it and a [BasePresenter] [P] to attach.
 */
abstract class BaseFragment<T : ViewBinding, P : BasePresenter<*>> : DaggerFragment() {

    /** The fragment handler is responsible of the communication between the fragment and the presenter. */
    @Inject
    protected lateinit var fragmentHandler: FragmentHandler<P>

    /** The presenter instance attached to the view. */
    protected val presenter: P get() = fragmentHandler.presenter

    /**
     * The binding object to obtain the view items.
     * It's valid between [onCreate] and [onDestroy].
     */
    protected val binding get() = _binding!!
    private var _binding: T? = null

    /**
     * A necessary abstract val that must be overridden with the [T] inflate method.
     *
     * Example
     * override val inflate: (inflater: LayoutInflater, container: ViewGroup?, attachToRoot: Boolean) -> ExampleBinding =
     *      ExampleBinding::inflate
     */
    abstract val inflate: (inflater: LayoutInflater, container: ViewGroup?, attachToRoot: Boolean) -> T

    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fragmentHandler.onCreate(this)
    }

    @CallSuper
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = inflate(inflater, container, false)
        return binding.root
    }

    @CallSuper
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    @CallSuper
    override fun onDestroy() {
        super.onDestroy()
        fragmentHandler.onDestroy()
    }
}