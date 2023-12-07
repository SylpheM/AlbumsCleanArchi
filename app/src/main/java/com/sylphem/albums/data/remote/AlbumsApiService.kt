package com.sylphem.albums.data.remote

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.sylphem.albums.data.model.RemoteAlbumItem
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.http.GET

interface AlbumsApiService {

    @GET("img/shared/technical-test.json")
    suspend fun getAlbumsList(): List<RemoteAlbumItem>

    companion object {
        private const val BASE_URL = "https://static.leboncoin.fr/"

        fun create(): AlbumsApiService {
            val client = OkHttpClient.Builder()
                .build()

            val contentType = "application/json".toMediaType()
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(Json.asConverterFactory(contentType))
                .build()
                .create(AlbumsApiService::class.java)
        }
    }
}