package com.example.composody.ui.faqs

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.composody.faqsdatabase.FAQsDatabaseDao

class FaqsViewModelFactory(
    private val dataSource: FAQsDatabaseDao,
    private val application: Application
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FaqsViewModel::class.java)) {
            // return an instance of the ViewModel
            return FaqsViewModel(dataSource, application) as T
        }
        // or throw an exception
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}