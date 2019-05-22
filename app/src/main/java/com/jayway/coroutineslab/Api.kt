package com.jayway.coroutineslab

import com.jayway.coroutineslab.model.Product
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {


    @GET("products?")
    fun searchProducts(@Query("search") string : String) : Call<List<Product>>

    companion object {

        fun create() : Api {
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://bolaget.io/v1/")
                .build()
            return retrofit.create(Api::class.java)
        }

    }

}