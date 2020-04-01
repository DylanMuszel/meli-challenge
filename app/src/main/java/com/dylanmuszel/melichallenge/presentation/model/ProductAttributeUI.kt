package com.dylanmuszel.melichallenge.presentation.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ProductAttributeUI(val name: String, val values: String) : Parcelable
