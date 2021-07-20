package com.thegeniusprogrammer.mvvmapp.network

import com.thegeniusprogrammer.mvvmapp.model.Posts
import retrofit2.http.GET

interface APIsRequest {
    @GET("posts")
    suspend fun getPosts():List<Posts>
}