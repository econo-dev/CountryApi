package com.gal.countryapi.network

import android.os.Parcelable
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.android.parcel.Parcelize
import kotlinx.coroutines.coroutineScope
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val  BASE_URL = "https://restcountries.eu/rest/v2/"


enum class SortCountry {
    SORT_A_Z,
    SORT_Z_A,
    SORT_GREATEST,
    SORT_SMALLEST
}

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()



interface ApiService {

    @GET("all")
    suspend fun getAll() : List<Country>
}

object CountryApi {
    val retrofitService : ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}