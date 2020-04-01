package com.dylanmuszel.melichallenge.framework.core

import android.util.Log
import com.dylanmuszel.melichallenge.BuildConfig
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Wrapper for the [Log] class that only log on productions messages with a level greater than [PRODUCTION_LOG_LEVEL].
 * It could also help to add Crashlytics on a future.
 */
@Singleton
class Logger @Inject constructor() {

    private val isDebug = BuildConfig.DEBUG

    /** Send a [Log.VERBOSE] log [message]. */
    fun v(tag: String, message: String, throwable: Throwable? = null) {
        if (shouldLog(Log.VERBOSE)) {
            throwable?.let { Log.v(tag, message, it) } ?: Log.v(tag, message)
        }
    }

    /** Send a [Log.DEBUG] log [message]. */
    fun d(tag: String, message: String, throwable: Throwable? = null) {
        if (shouldLog(Log.DEBUG)) {
            throwable?.let { Log.d(tag, message, it) } ?: Log.d(tag, message)
        }
    }

    /** Send a [Log.INFO] log [message]. */
    fun i(tag: String, message: String, throwable: Throwable? = null) {
        if (shouldLog(Log.INFO)) {
            throwable?.let { Log.i(tag, message, it) } ?: Log.i(tag, message)
        }
    }

    /** Send a [Log.WARN] log [message]. */
    fun w(tag: String, message: String, throwable: Throwable? = null) {
        if (shouldLog(Log.WARN)) {
            throwable?.let { Log.w(tag, message, it) } ?: Log.w(tag, message)
        }
    }

    /** Send a [Log.ERROR] log [message]. */
    fun e(tag: String, message: String, throwable: Throwable? = null) {
        if (shouldLog(Log.ERROR)) {
            throwable?.let { Log.e(tag, message, it) } ?: Log.e(tag, message)
        }
    }

    /** Send a [Log.ASSERT] log [message]. */
    fun wtf(tag: String, message: String, throwable: Throwable? = null) {
        if (shouldLog(Log.ASSERT)) {
            throwable?.let { Log.wtf(tag, message, it) } ?: Log.wtf(tag, message)
        }
    }

    /** Returns true if the application should log the passed log level. */
    private fun shouldLog(logLevel: Int) = isDebug || logLevel >= PRODUCTION_LOG_LEVEL

    companion object {

        private const val PRODUCTION_LOG_LEVEL = Log.WARN
    }
}
