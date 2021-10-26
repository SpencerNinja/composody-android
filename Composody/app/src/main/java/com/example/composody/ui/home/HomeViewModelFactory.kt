package com.example.composody.ui.home

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class HomeViewModelFactory(
    private val application: Application
    ) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            // return an instance of the ViewModel
            return HomeViewModel(application) as T
        }
        // or throw an exception
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}