package com.dylanmuszel.melichallenge.presentation.model

import androidx.annotation.ColorRes
import androidx.annotation.StringRes
import com.dylanmuszel.melichallenge.R

enum class SellerStatusUI(@ColorRes val colorRes: Int, @StringRes val textRes: Int) {
    GOLD(R.color.gold, R.string.product_seller_status_gold),
    PLATINUM(R.color.success_green, R.string.product_seller_status_platinum)
}
