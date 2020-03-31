package com.dylanmuszel.melichallenge.presentation.core

import android.os.Bundle

inline fun <reified T> Bundle.getSerializableList(key: String): List<T>? =
    getSerializable(key)?.let { it as ArrayList<*> }?.filterIsInstance<T>()