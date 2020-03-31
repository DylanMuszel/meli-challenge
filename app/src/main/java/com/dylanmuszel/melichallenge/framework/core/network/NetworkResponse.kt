package com.dylanmuszel.melichallenge.framework.core.network

/** A wrapper of the [T] result representing the network response. */
data class NetworkResponse<T>(val results: T)
