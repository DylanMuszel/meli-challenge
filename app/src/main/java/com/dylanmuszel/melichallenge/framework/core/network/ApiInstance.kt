package com.dylanmuszel.melichallenge.framework.core.network

import com.dylanmuszel.melichallenge.BuildConfig
import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Object responsible of creating the retrofit instance and services.
 */
@Singleton
class ApiInstance @Inject constructor(private val connectivityInfo: ConnectivityInfo) {

    /** Retrofit instance for the given [BASE_URL]. */
    private var _retrofit: Retrofit? = null
    private val retrofit: Retrofit
        get() = _retrofit ?: run {
            _retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(
                    GsonConverterFactory.create(
                        GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create()
                    )
                )
                .client(
                    OkHttpClient
                        .Builder()
                        .apply {
                            addInterceptor {
                                if (!connectivityInfo.isOnline) {
                                    throw NoNetworkException
                                }

                                it.proceed(it.request().newBuilder().build())
                            }
                            if (BuildConfig.DEBUG) {
                                val logging = HttpLoggingInterceptor().apply {
                                    level = HttpLoggingInterceptor.Level.BODY
                                }
                                addInterceptor(logging)
                            }
                        }
                        .build()
                )
                .build()
            _retrofit!!
        }

    /** Creates and returns an implementation of the API endpoints of the given [clazz]. */
    fun <T> getService(clazz: Class<T>): T = retrofit.create(clazz)

    companion object {
        private const val SITE_ID = "MLA"
        private const val BASE_URL = "https://api.mercadolibre.com/sites/$SITE_ID/"
    }
}

/** Custom exception for no network connection. */
object NoNetworkException : IOException("No network connection")

