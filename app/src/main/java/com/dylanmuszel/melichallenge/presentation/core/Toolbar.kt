package com.dylanmuszel.melichallenge.presentation.core

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageButton
import androidx.core.content.ContextCompat
import androidx.core.view.updatePadding
import com.dylanmuszel.melichallenge.R

/**
 * Toolbar custom view allowing to reuse the component on different views.
 */
class Toolbar @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    /** Callback invoked when the search button is clicked. */
    var onSearchClicked: (() -> Unit)? = null

    init {
        View.inflate(context, R.layout.view_toolbar, this)
        setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimary))
        val fragmentPadding = context.resources.getDimensionPixelSize(R.dimen.fragment_padding)
        val verticalPadding = context.resources.getDimensionPixelSize(R.dimen.spacing_medium_more)
        updatePadding(
            left = fragmentPadding,
            right = fragmentPadding,
            top = verticalPadding,
            bottom = verticalPadding
        )

        findViewById<ImageButton>(R.id.search_button).setOnClickListener {
            onSearchClicked?.invoke()
        }
    }
}