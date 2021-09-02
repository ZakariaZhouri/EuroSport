package com.example.eurosport.viewModel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.eurosport.repository.network.client.MainRepository

class ViewModelFactory(val context: Context?, private val repository: MainRepository): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(VideoStorieViewModel::class.java)) {
            VideoStorieViewModel(context,this.repository) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }    }
}