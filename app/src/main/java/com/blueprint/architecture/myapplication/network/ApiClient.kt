package com.blueprint.architecture.myapplication.network

import okhttp3.ConnectionPool
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.io.IOException
import java.util.concurrent.TimeUnit

/**
 * Created by Wajid Ali
 */
object ApiClient {
    private const val URL_BASE = "https://api.themoviedb.org/3/"
    const val IMAGES_BASE_PATH = "https://image.tmdb.org/t/p/w500/"
    private const val API_KEY = "4048cc551651721ca0145c5dfc617f1a"

    private var retrofit: Retrofit? = null
    val retrofitInstance: Retrofit?
        get() {
            if (retrofit == null) {
                retrofit = Retrofit.Builder()
                        .baseUrl(URL_BASE)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build()
            }
            return retrofit
        }

    // set your desired log level
    val clientAuthentication: Retrofit?
        get() {
            val logging = HttpLoggingInterceptor()
            // set your desired log level
            logging.level = HttpLoggingInterceptor.Level.BODY
            val httpClient = OkHttpClient.Builder()
                    .readTimeout(60, TimeUnit.SECONDS)
                    .connectTimeout(10, TimeUnit.SECONDS)
                    .connectionPool(ConnectionPool(0, 5, TimeUnit.SECONDS))
            val builder = Retrofit.Builder()
                    .baseUrl(URL_BASE)
            val interceptor = AuthenticationInterceptor(API_KEY)
            if (!httpClient.interceptors().contains(interceptor)) {
                httpClient.addInterceptor(interceptor)
                httpClient.addInterceptor(logging)
                builder.client(httpClient.build())
                retrofit = builder
                        .addConverterFactory(ScalarsConverterFactory.create())
                        .addConverterFactory(GsonConverterFactory.create())
                        .client(httpClient.build())
                        .build()
            }
            return retrofit
        }

    class AuthenticationInterceptor(private val apiKey: String) : Interceptor {
        @Throws(IOException::class)
        override fun intercept(chain: Interceptor.Chain): Response {
            val request = chain.request()
            val httpUrl = request.url()
            val newHttpUrl = httpUrl
                    .newBuilder()
                    .addQueryParameter("api_key", apiKey)
                    .build()
            val newRequest = request.newBuilder()
                    .url(newHttpUrl)
                    .build()
            return chain.proceed(newRequest)
        }

    }
}