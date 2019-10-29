package com.example.mortgagerates

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {



    @GET("jsonI.php")

    fun getNum(

        @Query("length") length: String,

        @Query("type" ) type: String

    ): Single<Numbers>











}