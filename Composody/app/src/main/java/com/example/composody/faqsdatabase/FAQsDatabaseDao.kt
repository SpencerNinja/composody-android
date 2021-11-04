package com.example.composody.faqsdatabase

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface FAQsDatabaseDao {

    @Insert
    suspend fun insert(faq: FAQ)

    @Update
    suspend fun update(faq: FAQ)

    @Query("SELECT * from faqs_table")
    suspend fun getFAQ(): FAQ?

    @Query("SELECT * FROM faqs_table ORDER BY faqsId DESC")
    fun getAllFAQs(): List<String>

}