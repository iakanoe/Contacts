package io.github.iakanoe.contacts.source.builder

import io.github.iakanoe.contacts.source.api.ContactApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitBuilder {
    private const val BASE_URL = "https://s3.amazonaws.com/technical-challenge/v3/"

    fun provideRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun provideContactsAPI(retrofit: Retrofit): ContactApi = retrofit.create(ContactApi::class.java)
}