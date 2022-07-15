package com.rememed.rememed.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL ="http://64.225.69.222:3000/"
object RetrofitInstance {
    private val interceptorLogging = HttpLoggingInterceptor().apply{
        level = HttpLoggingInterceptor.Level.BODY
    }
    private var token = ""

    fun setToken(value: String){
        token = value
    }
    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(
            OkHttpClient()
                .newBuilder()
                .addInterceptor{ chain ->
                    chain.proceed(
                        chain
                            .request()
                            .newBuilder()
                            .addHeader("Authorization","BEARER $token")
                            .build()
                    )
                }
                .addInterceptor(interceptorLogging)
                .build()
        )
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun getWordServices(): ProjectService{
        return retrofit.create(ProjectService::class.java)
    }
}