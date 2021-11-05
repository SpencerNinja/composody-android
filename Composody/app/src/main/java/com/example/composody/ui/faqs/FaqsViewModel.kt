package com.example.composody.ui.faqs

import android.app.Application
import androidx.lifecycle.*
import com.example.composody.faqsdatabase.FAQ
import com.example.composody.faqsdatabase.FAQsDatabase
import com.example.composody.faqsdatabase.FAQsDatabaseDao
import kotlinx.coroutines.*

class FaqsViewModel(
    application: Application
) : AndroidViewModel(application) {

        // Create a viewModelJob and override onCleared() for canceling coroutines
        private var viewModelJob = Job()

        private val database = FAQsDatabase.getInstance(application).faqsDatabaseDao

        override fun onCleared() {
            super.onCleared()
            viewModelJob.cancel()
        }
        // Define a scope for the coroutines to run in
        private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

        // Create faq live data
        private var faq = MutableLiveData<FAQ?>()

        // Get all nights from the database
        private val faqs = database.getAllFAQs()

        // Initialize
        private fun initializeTonight() {
            uiScope.launch {
                faq.value = getFAQFromDatabase()
            }
        }

        private suspend fun getFAQFromDatabase(): FAQ? {
            return withContext(Dispatchers.IO) {
                var faq = database.getFAQ()
                faq
            }
        }

    //    private val _text = MutableLiveData<String>().apply {
    //        value = "This is FAQs Fragment"
    //    }
    //    val text: LiveData<String> = _text

}