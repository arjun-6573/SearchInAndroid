package com.example.codeinandroid.external.remote

import com.example.codeinandroid.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class RequestInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response = chain.run {
        val url = chain.request().url.newBuilder()
            .addQueryParameter("client_id", BuildConfig.CLIENT_ID).build()
        val requestBuilder = request()
            .newBuilder()
        requestBuilder.method(request().method, request().body)
            .url(url)
        proceed(requestBuilder.build())
    }
}