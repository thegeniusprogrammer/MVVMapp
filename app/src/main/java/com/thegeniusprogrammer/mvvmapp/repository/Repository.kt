package com.thegeniusprogrammer.mvvmapp.repository

import com.thegeniusprogrammer.mvvmapp.model.Posts
import com.thegeniusprogrammer.mvvmapp.network.RetrofitInstance
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class Repository {

    fun  getPosts():Flow<List<Posts>> = flow {
        emit(RetrofitInstance.api.getPosts())
    }.flowOn(IO)
}