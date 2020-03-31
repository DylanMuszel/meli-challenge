package com.dylanmuszel.core.fp

import java.lang.Exception


sealed class Failure

/** Failure to return when the device is offline. */
object NetworkConnection : Failure()

/**
 * Failure to return when the server returns an unexpected error.
 *
 * It could be extended in case of an expected server error.
 */
open class ServerError(val exception: Exception) : Failure()

/** Failure to be extended for feature specific failures. */
abstract class FeatureFailure : Failure()
