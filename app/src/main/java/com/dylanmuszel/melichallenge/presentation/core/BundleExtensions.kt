package com.dylanmuszel.melichallenge.presentation.core

import android.os.Bundle

/**
 * Returns the serializable list from the bundle associated to the [key].
 */
inline fun <reified T> Bundle.getSerializableList(key: String): List<T>? =
    getSerializable(key)?.let { it as ArrayList<*> }?.filterIsInstance<T>()
