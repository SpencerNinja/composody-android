package com.example.composody.faqsdatabase

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface FAQsDatabaseDao {

    @Insert
    suspend fun insert(faq: FAQs)

    @Update
    suspend fun update(faq: FAQs)

    @Query("SELECT * from faqs_table WHERE faqsId = :key")
    suspend fun get(key: Long): FAQs?

//    @Query("SELECT * FROM faqs_table ORDER BY faqsId DESC")
//    fun getAllFAQs() List<String>

}