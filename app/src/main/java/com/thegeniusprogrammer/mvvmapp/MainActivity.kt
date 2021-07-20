package com.thegeniusprogrammer.mvvmapp

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.thegeniusprogrammer.mvvmapp.adapters.Adapter
import com.thegeniusprogrammer.mvvmapp.databinding.ActivityMainBinding
import com.thegeniusprogrammer.mvvmapp.repository.Repository
import com.thegeniusprogrammer.mvvmapp.utils.APIsResponse
import com.thegeniusprogrammer.mvvmapp.utils.logMessage
import com.thegeniusprogrammer.mvvmapp.viewmodel.MainViewModel
import com.thegeniusprogrammer.mvvmapp.viewmodel.ViewModelFactory
import kotlinx.coroutines.flow.collect

class MainActivity : AppCompatActivity() {
    lateinit var mAdapter: Adapter
    lateinit var binding: ActivityMainBinding

    val viewModel: MainViewModel by viewModels {
        ViewModelFactory(Repository())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
         mAdapter = Adapter()
        setupRecyclerView()

        viewModel.getPosts()
        lifecycleScope.launchWhenCreated {
            viewModel._postStateFlow.collect { apiState ->
              when(apiState){
                  is APIsResponse.Loading->{

                  }
                  is APIsResponse.Failed->{
                      logMessage(print = apiState.error.toString())
                  }
                  is APIsResponse.Success->{
                      binding.progressBar.visibility = View.GONE
                      mAdapter.differ.submitList(apiState.data)
                  }
                  is APIsResponse.Empty->{
                    logMessage(print = "")

                  }
              }

            }
        }


    }

    private fun setupRecyclerView(){
       binding.rv.apply {
           layoutManager = LinearLayoutManager(this@MainActivity)
           adapter = mAdapter
       }
    }
}