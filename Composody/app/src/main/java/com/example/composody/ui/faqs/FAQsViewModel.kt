package com.example.composody.ui.faqs

import android.app.Application
import androidx.lifecycle.*
import com.example.composody.faqsdatabase.FAQ
import com.example.composody.faqsdatabase.FAQsDatabase
import com.example.composody.faqsdatabase.FAQsDatabaseDao
import kotlinx.coroutines.*

class FAQsViewModel(
    val database: FAQsDatabaseDao,
    application: Application
) : ViewModel() {

        // Create a viewModelJob and override onCleared() for canceling coroutines
        private var viewModelJob = Job()

        private val databaseDao = FAQsDatabase.getInstance(application).faqsDatabaseDao

        private val _selectedFAQ = MutableLiveData<FAQ?>()
        val selectedFAQ: LiveData<FAQ?>
            get() = _selectedFAQ

        fun setFAQ(faq: FAQ) {
            _selectedFAQ.value = faq
        }

        override fun onCleared() {
            super.onCleared()
            viewModelJob.cancel()
        }
        // Define a scope for the coroutines to run in
        private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

        // Create faq live data
        private var faq = MutableLiveData<FAQ?>()

        // Get all nights from the database
        private val faqs = databaseDao.getAllFAQs()

        // Initialize
        private fun initializeTonight() {
            uiScope.launch {
                faq.value = getFAQFromDatabase()
            }
        }

        private suspend fun getFAQFromDatabase(): FAQ? {
            return withContext(Dispatchers.IO) {
                var faq = databaseDao.getFAQ()
                faq
            }
        }

    //    private val _text = MutableLiveData<String>().apply {
    //        value = "This is FAQs Fragment"
    //    }
    //    val text: LiveData<String> = _text

}