package com.thegeniusprogrammer.mvvmapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thegeniusprogrammer.mvvmapp.repository.Repository
import com.thegeniusprogrammer.mvvmapp.utils.APIsResponse
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

 class MainViewModel(private val repository: Repository) : ViewModel() {

    private val postFlow: MutableStateFlow<APIsResponse> = MutableStateFlow(APIsResponse.Empty)
     val _postStateFlow:StateFlow<APIsResponse> = postFlow


     init {
         getPosts()
     }

    private fun getPosts() = viewModelScope.launch(IO) {
        postFlow.value = APIsResponse.Loading
        repository.getPosts().catch {
            postFlow.value = APIsResponse.Failed(error = it)
        }.collect {
            postFlow.value = APIsResponse.Success(it)
        }
    }

    


}