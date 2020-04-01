package com.dylanmuszel.melichallenge.presentation.model

import android.os.Parcelable
import androidx.annotation.StringRes
import com.dylanmuszel.domain.ProductAttribute
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ProductUI(
    val title: String,
    val price: String,
    val thumbnail: String,
    @StringRes val condition: Int,
    val availableQuantity: Int,
    val soldQuantity: Int,
    val sellerStatus: SellerStatusUI?,
    val address: String,
    val attributes: List<ProductAttributeUI>
) : Parcelable
