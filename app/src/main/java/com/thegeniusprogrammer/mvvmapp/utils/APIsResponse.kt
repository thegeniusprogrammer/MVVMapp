package com.thegeniusprogrammer.mvvmapp.utils

import com.thegeniusprogrammer.mvvmapp.model.Posts

sealed class APIsResponse {
    object Loading:APIsResponse()
    class Success(val data:List<Posts>):APIsResponse()
    class Failed(val error:Throwable):APIsResponse()
    object Empty:APIsResponse()
}