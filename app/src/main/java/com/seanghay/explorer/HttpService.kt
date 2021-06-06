package com.seanghay.explorer

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.GET

interface HttpService {

    @GET("/posts")
    suspend fun posts(): List<DataItem>

    companion object {
        private val retrofit = Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create()).build()
        private val service: HttpService = retrofit.create()
        operator fun invoke(): HttpService = service
    }
}

data class DataItem(
    val id: Int? = null,
    val title: String? = null
)