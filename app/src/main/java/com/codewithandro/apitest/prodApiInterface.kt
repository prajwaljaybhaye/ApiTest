package com.codewithandro.apitest

import retrofit2.Call
import retrofit2.http.GET

interface prodApiInterface {

    @GET("products")
    fun prodGetData():Call<prodMainFile>

}