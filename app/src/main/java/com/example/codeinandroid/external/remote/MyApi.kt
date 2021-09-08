package com.example.codeinandroid.external.remote

import retrofit2.Response
import retrofit2.http.*


interface MyApi {
    @GET("events")
    suspend fun searchArtistAsync(@Query("q") searchKey: String): Response<EventsResponseModel>
}