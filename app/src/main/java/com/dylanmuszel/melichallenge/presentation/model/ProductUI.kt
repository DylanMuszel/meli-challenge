package com.dylanmuszel.melichallenge.presentation.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ProductUI(val title: String, val price: String, val thumbnail: String) : Parcelable