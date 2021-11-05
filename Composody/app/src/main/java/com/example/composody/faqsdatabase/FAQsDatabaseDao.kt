package com.example.composody.faqsdatabase

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface FAQsDatabaseDao {

    @Insert
    suspend fun insert(faq: FAQ)

    @Insert
    suspend fun insertAllFAQs(faq: List<FAQ>)

    @Update
    suspend fun update(faq: FAQ)

    @Query("SELECT * from faqs_table")
    suspend fun getFAQ(): FAQ?

    @Query("SELECT * FROM faqs_table ORDER BY faqsId DESC")
    fun getAllFAQs(): LiveData<List<String>>

}