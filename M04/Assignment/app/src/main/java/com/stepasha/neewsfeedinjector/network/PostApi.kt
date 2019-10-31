package com.stepasha.neewsfeedinjector.network

import com.stepasha.neewsfeedinjector.model.Post
import io.reactivex.Observable
import retrofit2.http.GET

/**
 * The interface which provides methods to get result of webservices
 */
interface PostApi {
    /**
     * Get the list of the pots from the API
     */
    @GET("/posts")
    //todo reactiveX
    fun getPosts(): Observable<List<Post>>
}