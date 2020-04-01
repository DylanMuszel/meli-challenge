package com.dylanmuszel.core.fp

sealed class Failure

/** Failure to return when the device is offline. */
object NetworkConnectionFailure : Failure()

/**
 * Failure to return when the server returns an unexpected error.
 *
 * It could be extended in case of an expected server error.
 */
open class ServerFailure(val exception: Throwable) : Failure()

/** Failure to be extended for feature specific failures. */
abstract class FeatureFailure : Failure()
