package com.example.mortgagerates

import com.google.gson.GsonBuilder
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {



    @GET("jsonI.php")

    fun getNum(

        @Query("length") length: Int,

        @Query("type" ) type: String

    ): Single<Numbers>

    object InitializeRetro {

        fun startRetroCalls(): Api {

            return Retrofit.Builder()

                .baseUrl("http://qrng.anu.edu.au/API/")

                .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))

                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())

                .build()

                .create(Api::class.java)

        }
    }










}