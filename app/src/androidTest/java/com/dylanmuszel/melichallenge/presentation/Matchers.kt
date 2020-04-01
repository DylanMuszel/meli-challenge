package com.dylanmuszel.melichallenge.presentation

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.toBitmap
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.matcher.BoundedMatcher
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher


fun atPosition(position: Int, itemMatcher: Matcher<View>): Matcher<View?>? {

    return object : BoundedMatcher<View?, RecyclerView>(RecyclerView::class.java) {

        override fun describeTo(description: Description) {
            description.appendText("has item at position $position: ")
            itemMatcher.describeTo(description)
        }

        override fun matchesSafely(view: RecyclerView): Boolean {
            val viewHolder = view.findViewHolderForAdapterPosition(position) ?: return false
            return itemMatcher.matches(viewHolder.itemView)
        }
    }
}

fun withTextColor(color: Int) = object : BoundedMatcher<View, TextView>(TextView::class.java) {

    override fun matchesSafely(warning: TextView): Boolean {
        return color == warning.currentTextColor
    }

    override fun describeTo(description: Description) {
        description.appendText("with text color: ")
    }
}
