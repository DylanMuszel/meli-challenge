package com.dylanmuszel.melichallenge.presentation.core

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import androidx.annotation.StringRes
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.view.updatePadding
import com.dylanmuszel.melichallenge.R
import org.w3c.dom.Text

/**
 * Toolbar custom view allowing to reuse the component on different views.
 */
class Toolbar @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val title: TextView

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
        title = findViewById(R.id.title)
    }

    /** Set the toolbar title with a string resource. */
    fun setTitle(@StringRes stringRes: Int, vararg formatArgs: Any = emptyArray()) {
        title.text = context.resources.getString(stringRes, *formatArgs)
    }
}